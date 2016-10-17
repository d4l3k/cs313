// THIS IS THIS SOLUTION TO CPSC 313 HOMEWORK 2 and the starting point for HOMEWORK 3.
// DO NOT RE-DISTRIBUTE AND DO NOT REMOVE THIS MESSAGE

package arch.y86.machine.pipe.student;

import machine.AbstractMainMemory;
import machine.Register;
import machine.RegisterSet;
import ui.Machine;
import arch.y86.machine.AbstractY86CPU;
import java.util.Stack;
import java.util.HashMap;

/**
 * The Simple Machine CPU.
 *
 * Simulate execution of a single cycle of the Simple Machine Y86-Pipe CPU.
 */

public class CPU extends AbstractY86CPU.Pipelined {
    private static final int RETURN_CACHE_SIZE = 100;
    private static final int JUMP_CACHE_BITS = 4;

    private Stack<Integer> returnCache = new Stack<Integer>();
    private HashMap<Integer, Integer> jumpCache = new HashMap<Integer, Integer>();

    public CPU(String name, AbstractMainMemory memory) {
        super(name, memory);
    }

    /**
     * Execute one clock cycle with all stages executing in parallel.
     * @throws InvalidInstructionException                if instruction is invalid (including
     * invalid register number).
     * @throws AbstractMainMemory.InvalidAddressException if instruction attemps an invalid memory
     * access (either instruction or data).
     * @throws MachineHaltException                       if instruction halts the CPU.
     * @throws Register.TimingException
     */
    @Override
    protected void cycle()
        throws InvalidInstructionException, AbstractMainMemory.InvalidAddressException,
               MachineHaltException, Register.TimingException, ImplementationException {
        System.out.println("######### CYCLE #########");
        cyclePipe();
    }

    /**
     * Pipeline Hazard Control
     *
     * IMPLEMENTED BY STUDENT
     */

    @Override
    protected void pipelineHazardControl() throws Register.TimingException {
        // Control-Hazard: Conditional Jump
        // Shoot down inflight operations that are mispredicted.
        if (MisMispredicted()) {
            M.bubble = true;
            E.bubble = true;

            // Remove entries from the returnCache if mispredicted I_CALL.
            if (D.iCd.get() == I_CALL) {
                if (!this.returnCache.empty()) {
                    this.returnCache.pop();
                }
            }
            if (E.iCd.get() == I_CALL) {
                if (!this.returnCache.empty()) {
                    this.returnCache.pop();
                }
            }
            // Restore the returnCache since the RET got shot down.
            if (D.iCd.get() == I_RET && D.iFn.get() == 1) {
                this.returnCache.push(D.valC.get());
            }
            if (E.iCd.get() == I_RET && E.iFn.get() == 1) {
                this.returnCache.push(E.valC.get());
            }

            // Return since we don't need to avoid the data hazards on the mispredicted branch.
            return;
        }

        // Data-Hazard: Load-Use
        if ((d.srcA.getValueProduced() != R_NONE && d.srcA.getValueProduced() == E.dstM.get())
            || (d.srcB.getValueProduced() != R_NONE && d.srcB.getValueProduced() == E.dstM.get())) {
            System.out.println("Load-Use: stalling F");
            F.stall = true;
            D.stall = true;
            E.bubble = true;
        }

        // Control-Hazard: RET
        if ((D.iCd.get() == I_RET && D.iFn.get() != 1)
            || (E.iCd.get() == I_RET && E.iFn.get() != 1)
            || (M.iCd.get() == I_RET && M.iFn.get() != 1)) {
            System.out.println("RET: stalling F");
            F.stall = true;
            D.bubble = true;
        }

        // If we stall F, push the returnCache value back into the cache to reuse it.
        if (F.stall && f.iCd.getValueProduced() == I_RET && f.iFn.getValueProduced() == 1) {
            System.out.println(
                "pipelineHazardControl: stalling F, thus returning prPC to returnCache");
            this.returnCache.push(f.prPC.getValueProduced());
        }
    }

    private int McorrectPC() {
      return (M.cnd.get() == 0) ? M.valP.get() : M.valC.get();
    }

    private boolean MisMispredicted() {
      int Epc = E.valP.get() - insSize(E.iCd.get());
      return M.iCd.get() == I_JXX && M.iFn.get() != C_NC && Epc != McorrectPC();
    }


    /**
     * The SelectPC part of the fetch stage
     *
     * IMPLEMENTED BY STUDENT
     */

    @Override
    protected void fetch_SelectPC() throws Register.TimingException {
        // Forward computed return value (don't if we pulled value from return cache).
        if (W.iCd.get() == I_RET && W.iFn.get() != 1) {
            f.pc.set(W.valM.get());
            // Forward mispredicted jump.
        } else if (MisMispredicted()) {
            f.pc.set(McorrectPC());
            System.out.printf("selectPC: mispredicted jump\n", M.valP.get());
            // Default behavior
        } else {
            System.out.printf("selectPC: normal\n");
            f.pc.set(F.prPC.get());
        }
        System.out.printf("selectPC: f.pc = %d\n", f.pc.getValueProduced());
    }

    /**
     * PC prediction part of FETCH stage.  Predicts PC to fetch in next cycle.
     * Writes the predicted PC into the f.prPC register.
     *
     * IMPLEMENTED BY STUDENT
     */

    @SuppressWarnings("fallthrough")
    private void fetch_PredictPC() throws Register.TimingException {
        // Predict PC if in return cache.
        if (f.stat.getValueProduced() == S_AOK) {
            switch (f.iCd.getValueProduced()) {
                case I_JXX:
                case I_CALL:
                    System.out.println("predictPC: predict jump");
                    // Always predict jump for C_NC.
                    if (f.iFn.getValueProduced() == C_NC) {
                        f.prPC.set(f.valC.getValueProduced());
                        break;
                    }
                    int x = 0;
                    int pc = f.valP.getValueProduced() - 5;
                    if (!jumpCache.containsValue(pc)) {
                      //jumpCache.put(pc, 0b11111111);
                      jumpCache.put(pc, 0);
                    }
                    for (int bits = jumpCache.get(pc); bits != 0; bits = bits >>> 1) {
                        x += bits & 1;
                    }
                    f.prPC.set((x > (JUMP_CACHE_BITS * 2 / 3)) ? f.valC.getValueProduced()
                                                               : f.valP.getValueProduced());
                    break;
                case I_RET:
                    if (!this.returnCache.empty()) {
                        f.prPC.set(this.returnCache.pop());
                        f.iFn.set(1);
                        // Set valC so we can restore the returnCache if this RET gets mispredicted.
                        f.valC.set(f.prPC.getValueProduced());
                        System.out.println("predictPC: from returnCache ========");
                        if (F.stall) {
                            System.out.println(
                                "predictPC: stall detected: returning to returnCache");
                            this.returnCache.push(f.prPC.getValueProduced());
                        }
                        break;
                    }
                default:
                    f.prPC.set(f.valP.getValueProduced());
                    System.out.println("predictPC: valP");
            }
        } else {
            f.prPC.set(f.pc.getValueProduced());
            System.out.println("predictPC: pc");
        }
        System.out.printf("predictPC: f.prPC = %d\n", f.prPC.getValueProduced());
    }

    /**
     * The FETCH stage of CPU
     * @throws Register.TimingException
     */

    @Override
    protected void fetch() throws Register.TimingException {
        try {
            // determine correct PC for this stage
            fetch_SelectPC();

            // get iCd and iFn
            f.iCd.set(mem.read(f.pc.getValueProduced(), 1)[0].value() >>> 4);
            f.iFn.set(mem.read(f.pc.getValueProduced(), 1)[0].value() & 0xf);

            System.out.printf("fetch: f.pc = %d, iCd = %d, iFn = %d, I_HALT = %d\n",
                f.pc.getValueProduced(), f.iCd.getValueProduced(), f.iFn.getValueProduced(),
                I_HALT);

            // stat MUX
            switch (f.iCd.getValueProduced()) {
                case I_HALT:
                case I_NOP:
                case I_IRMOVL:
                case I_RMMOVL:
                case I_MRMOVL:
                case I_RET:
                case I_PUSHL:
                case I_POPL:
                case I_CALL:
                    switch (f.iFn.getValueProduced()) {
                        case 0x0:
                            f.stat.set(S_AOK);
                            break;
                        default:
                            f.stat.set(S_INS);
                            break;
                    }
                    break;
                case I_RRMVXX:
                case I_JXX:
                    switch (f.iFn.getValueProduced()) {
                        case C_NC:
                        case C_LE:
                        case C_L:
                        case C_E:
                        case C_NE:
                        case C_GE:
                        case C_G:
                            f.stat.set(S_AOK);
                            break;
                        default:
                            f.stat.set(S_INS);
                    }
                    break;
                case I_OPL:
                    switch (f.iFn.getValueProduced()) {
                        case A_ADDL:
                        case A_SUBL:
                        case A_ANDL:
                        case A_XORL:
                        case A_MULL:
                        case A_DIVL:
                        case A_MODL:
                            f.stat.set(S_AOK);
                            break;
                        default:
                            f.stat.set(S_INS);
                            break;
                    }
                    break;
                default:
                    f.stat.set(S_INS);
                    break;
            }

            if (f.stat.getValueProduced() == S_AOK) {
                // rA MUX
                switch (f.iCd.getValueProduced()) {
                    case I_HALT:
                        f.rA.set(R_NONE);
                        f.stat.set(S_HLT);
                        break;
                    case I_RRMVXX:
                    case I_RMMOVL:
                    case I_MRMOVL:
                    case I_OPL:
                    case I_PUSHL:
                    case I_POPL:
                        f.rA.set(mem.read(f.pc.getValueProduced() + 1, 1)[0].value() >>> 4);
                        break;
                    default:
                        f.rA.set(R_NONE);
                }

                // rB MUX
                switch (f.iCd.getValueProduced()) {
                    case I_RRMVXX:
                    case I_IRMOVL:
                    case I_RMMOVL:
                    case I_MRMOVL:
                    case I_OPL:
                        f.rB.set(mem.read(f.pc.getValueProduced() + 1, 1)[0].value() & 0xf);
                        break;
                    default:
                        f.rB.set(R_NONE);
                }

                // valC MUX
                switch (f.iCd.getValueProduced()) {
                    case I_IRMOVL:
                    case I_RMMOVL:
                    case I_MRMOVL:
                        f.valC.set(mem.readIntegerUnaligned(f.pc.getValueProduced() + 2));
                        break;
                    case I_JXX:
                    case I_CALL:
                        f.valC.set(mem.readIntegerUnaligned(f.pc.getValueProduced() + 1));
                        break;
                    default:
                        f.valC.set(0);
                }

                // valP MUX
                f.valP.set(f.pc.getValueProduced() + insSize(f.iCd.getValueProduced()));

                // Save valP into the return cache.
                if (f.iCd.getValueProduced() == I_CALL) {
                  this.returnCache.push(f.valP.getValueProduced());
                  if (this.returnCache.size() > RETURN_CACHE_SIZE) {
                    this.returnCache.remove(0);
                  }
                  System.out.printf(
                      "fetch: returnCache.size() = %d\n", this.returnCache.size());
                }
            }
        } catch (AbstractMainMemory.InvalidAddressException iae) {
            f.stat.set(S_ADR);
        }

        // predict PC for next cycle
        fetch_PredictPC();
    }

    private int insSize(int iCd) {
      switch (iCd) {
        case I_NOP:
        case I_HALT:
        case I_RET:
          return 1;
        case I_RRMVXX:
        case I_OPL:
        case I_PUSHL:
        case I_POPL:
          return 2;
        case I_JXX:
        case I_CALL:
          return 5;
        case I_IRMOVL:
        case I_RMMOVL:
        case I_MRMOVL:
          return 6;
        default:
          throw new AssertionError();
      }
    }

    /**
     * Determine current value of specified register by employing data fowarding, where necessary.
     * STUDENT CHANGES THIS METHOD TO IMPLEMENT DATA FORWARDING
     * @param  regNum  number of register being read
     * @return value of register
     * @throws RegisterSet.InvalidRegisterNumberException if register number is invalid
     */
    private int decode_ReadRegisterWithForwarding(int regNum)
        throws RegisterSet.InvalidRegisterNumberException, Register.TimingException {
        if (regNum == R_NONE)
            return 0;

        // Forwarding from E to D.
        if (regNum == E.dstE.get() && e.cnd.getValueProduced() == 1)
            return e.valE.getValueProduced();

        // Forwarding from M to D.
        if (regNum == M.dstM.get())
            return m.valM.getValueProduced();

        if (regNum == M.dstE.get() && M.cnd.get() == 1)
            return M.valE.get();

        // Forwarding from W to D.
        if (regNum == W.dstM.get())
            return W.valM.get();

        if (regNum == W.dstE.get() && W.cnd.get() == 1)
            return W.valE.get();

        return reg.get(regNum);
    }

    /**
     * The DECODE stage of CPU
     * @throws Register.TimingException
     */

    @Override
    protected void decode() throws Register.TimingException {
        // pass-through signals
        d.stat.set(D.stat.get());
        d.iCd.set(D.iCd.get());
        d.iFn.set(D.iFn.get());
        d.valC.set(D.valC.get());
        d.valP.set(D.valP.get());

        if (D.stat.get() == S_AOK) {
            try {
                // srcA MUX
                switch (D.iCd.get()) {
                    case I_RRMVXX:
                    case I_RMMOVL:
                    case I_OPL:
                    case I_PUSHL:
                        d.srcA.set(D.rA.get());
                        break;
                    case I_RET:
                    case I_POPL:
                        d.srcA.set(R_ESP);
                        break;
                    default:
                        d.srcA.set(R_NONE);
                }

                // srcB MUX
                switch (D.iCd.get()) {
                    case I_RMMOVL:
                    case I_MRMOVL:
                    case I_OPL:
                        d.srcB.set(D.rB.get());
                        break;
                    case I_CALL:
                    case I_RET:
                    case I_PUSHL:
                    case I_POPL:
                        d.srcB.set(R_ESP);
                        break;
                    default:
                        d.srcB.set(R_NONE);
                }

                // dstE MUX
                switch (D.iCd.get()) {
                    case I_RRMVXX:
                    case I_IRMOVL:
                    case I_OPL:
                        d.dstE.set(D.rB.get());
                        break;
                    case I_CALL:
                    case I_RET:
                    case I_PUSHL:
                    case I_POPL:
                        d.dstE.set(R_ESP);
                        break;
                    default:
                        d.dstE.set(R_NONE);
                }

                // dstM MUX
                switch (D.iCd.get()) {
                    case I_MRMOVL:
                    case I_POPL:
                        d.dstM.set(D.rA.get());
                        break;
                    default:
                        d.dstM.set(R_NONE);
                }

                try {
                    d.valA.set(decode_ReadRegisterWithForwarding(d.srcA.getValueProduced()));
                    d.valB.set(decode_ReadRegisterWithForwarding(d.srcB.getValueProduced()));
                } catch (RegisterSet.InvalidRegisterNumberException irne) {
                    throw new InvalidInstructionException(irne);
                }
            } catch (InvalidInstructionException iie) {
                d.stat.set(S_INS);
            }
        }

        if (d.stat.getValueProduced() != S_AOK) {
            d.srcA.set(R_NONE);
            d.srcB.set(R_NONE);
            d.dstE.set(R_NONE);
            d.dstM.set(R_NONE);
        }
    }

    /**
     * The EXECUTE stage of CPU
     * @throws Register.TimingException
     */

    @Override
    protected void execute() throws Register.TimingException {
        // pass-through signals
        e.stat.set(E.stat.get());
        e.iCd.set(E.iCd.get());
        e.iFn.set(E.iFn.get());
        e.valC.set(E.valC.get());
        e.valA.set(E.valA.get());
        e.dstE.set(E.dstE.get());
        e.dstM.set(E.dstM.get());
        e.valP.set(E.valP.get());

        if (E.stat.get() == S_AOK) {
            // aluA MUX
            int aluA;
            switch (E.iCd.get()) {
                case I_RRMVXX:
                case I_OPL:
                    aluA = E.valA.get();
                    break;
                case I_IRMOVL:
                case I_MRMOVL:
                case I_RMMOVL:
                    aluA = E.valC.get();
                    break;
                case I_RET:
                case I_POPL:
                    aluA = 4;
                    break;
                case I_CALL:
                case I_PUSHL:
                    aluA = -4;
                    break;
                default:
                    aluA = 0;
            }

            // aluB MUX
            int aluB;
            switch (E.iCd.get()) {
                case I_RRMVXX:
                case I_IRMOVL:
                    aluB = 0;
                    break;
                case I_RMMOVL:
                case I_MRMOVL:
                case I_OPL:
                case I_CALL:
                case I_RET:
                case I_PUSHL:
                case I_POPL:
                    aluB = E.valB.get();
                    break;
                default:
                    aluB = 0;
            }

            // aluFun and setCC muxes MUX
            int aluFun;
            boolean setCC;
            switch (E.iCd.get()) {
                case I_RRMVXX:
                case I_IRMOVL:
                case I_RMMOVL:
                case I_MRMOVL:
                case I_CALL:
                case I_RET:
                case I_PUSHL:
                case I_POPL:
                    aluFun = A_ADDL;
                    setCC = false;
                    break;
                case I_OPL:
                    aluFun = E.iFn.get();
                    setCC = true;
                    break;
                default:
                    aluFun = 0;
                    setCC = false;
            }

            // the ALU
            boolean overflow;
            switch (aluFun) {
                case A_ADDL:
                    e.valE.set(aluB + aluA);
                    overflow = ((aluB < 0) == (aluA < 0))
                        && ((e.valE.getValueProduced() < 0) != (aluB < 0));
                    break;
                case A_SUBL:
                    e.valE.set(aluB - aluA);
                    overflow = ((aluB < 0) != (aluA < 0))
                        && ((e.valE.getValueProduced() < 0) != (aluB < 0));
                    break;
                case A_ANDL:
                    e.valE.set(aluB & aluA);
                    overflow = false;
                    break;
                case A_XORL:
                    e.valE.set(aluB ^ aluA);
                    overflow = false;
                    break;
                case A_MULL:
                    int result = aluB * aluA;
                    e.valE.set(result);
                    overflow = aluB != 0 && result / aluB != aluA;
                    break;
                case A_DIVL:
                    e.valE.set(aluA == 0 ? aluB : aluB / aluA);
                    overflow = aluA == 0;
                    break;
                case A_MODL:
                    e.valE.set(aluA == 0 ? aluB : aluB % aluA);
                    overflow = aluA == 0;
                    break;
                default:
                    overflow = false;
            }

            // CC MUX
            if (setCC)
                p.cc.set(((e.valE.getValueProduced() == 0) ? 0x100 : 0)
                    | ((e.valE.getValueProduced() < 0) ? 0x10 : 0) | (overflow ? 0x1 : 0));
            else
                p.cc.set(P.cc.get());

            // cnd MUX
            boolean cnd;
            switch (E.iCd.get()) {
                case I_JXX:
                case I_RRMVXX:
                    boolean zf = (P.cc.get() & 0x100) != 0;
                    boolean sf = (P.cc.get() & 0x010) != 0;
                    boolean of = (P.cc.get() & 0x001) != 0;
                    switch (E.iFn.get()) {
                        case C_NC:
                            cnd = true;
                            break;
                        case C_LE:
                            cnd = (sf ^ of) | zf;
                            break;
                        case C_L:
                            cnd = sf ^ of;
                            break;
                        case C_E:
                            cnd = zf;
                            break;
                        case C_NE:
                            cnd = !zf;
                            break;
                        case C_GE:
                            cnd = !(sf ^ of);
                            break;
                        case C_G:
                            cnd = !(sf ^ of) & !zf;
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                default:
                    cnd = true;
            }
            e.cnd.set(cnd ? 1 : 0);

        } else {
            e.cnd.set(0);
        }

        if (E.iCd.get() == I_JXX && E.iFn.get() != C_NC) {
            int pc = E.valP.get()-5;
            jumpCache.put(pc, (jumpCache.get(pc) << 1) | e.cnd.getValueProduced());
        }
    }

    /**
     * The MEMORY stage of CPU
     * @throws Register.TimingException
     */

    @Override
    protected void memory() throws Register.TimingException {
        // pass-through signals
        m.iCd.set(M.iCd.get());
        m.iFn.set(M.iFn.get());
        m.cnd.set(M.cnd.get());
        m.valE.set(M.valE.get());
        m.dstE.set(M.dstE.get());
        m.dstM.set(M.dstM.get());
        m.valP.set(M.valP.get());

        if (M.stat.get() == S_AOK) {
            try {
                // write Main Memory
                switch (M.iCd.get()) {
                    case I_RMMOVL:
                    case I_PUSHL:
                        mem.writeInteger(M.valE.get(), M.valA.get());
                        break;
                    case I_CALL:
                        // Push onto stack.
                        mem.writeInteger(M.valE.get(), M.valP.get());
                        break;
                    default:
                }

                // valM MUX (read main memory)
                switch (M.iCd.get()) {
                    case I_MRMOVL:
                        m.valM.set(mem.readInteger(M.valE.get()));
                        break;
                    case I_RET:
                    case I_POPL:
                        m.valM.set(mem.readInteger(M.valA.get()));
                        break;
                    default:
                }
                m.stat.set(M.stat.get());

            } catch (AbstractMainMemory.InvalidAddressException iae) {
                m.stat.set(S_ADR);
            }

        } else {
            m.stat.set(M.stat.get());
        }
    }

    /**
     * The WRITE BACK stage of CPU
     * @throws MachineHaltException                       if instruction halts the CPU (e.g., halt
     * instruction).
     * @throws InvalidInstructionException
     * @throws AbstractMainMemory.InvalidAddressException
     * @throws Register.TimingException
     */

    @Override
    protected void writeBack()
        throws MachineHaltException, InvalidInstructionException,
               AbstractMainMemory.InvalidAddressException, Register.TimingException {
        if (W.stat.get() == S_AOK)
            try {
                try {
                    // write valE to register file
                    if (W.dstE.get() != R_NONE && W.cnd.get() == 1)
                        reg.set(W.dstE.get(), W.valE.get());

                    // write valM to register file
                    if (W.dstM.get() != R_NONE)
                        reg.set(W.dstM.get(), W.valM.get());

                    w.stat.set(W.stat.get());

                } catch (RegisterSet.InvalidRegisterNumberException irne) {
                    throw new InvalidInstructionException(irne);
                }

            } catch (InvalidInstructionException iie) {
                w.stat.set(S_INS);
            }
        else
            w.stat.set(W.stat.get());

        if (w.stat.getValueProduced() == S_ADR)
            throw new AbstractMainMemory.InvalidAddressException();
        else if (w.stat.getValueProduced() == S_INS)
            throw new InvalidInstructionException();
        else if (w.stat.getValueProduced() == S_HLT)
            throw new MachineHaltException();
    }
}
