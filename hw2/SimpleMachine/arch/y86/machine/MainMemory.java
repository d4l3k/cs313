package arch.y86.machine;

import machine.AbstractMainMemory;
import util.UnsignedByte;

public class MainMemory extends AbstractMainMemory {
  private byte [] mem;
  
  public MainMemory (int byteCapacity) {
    mem = new byte [byteCapacity];
  }
  
  @Override
  protected boolean isAccessAligned (int address, int length) {
    return address % length == 0;
  }
  
  @Override public int bytesToInteger (byte byteAtAddrPlus0, byte byteAtAddrPlus1, byte byteAtAddrPlus2, byte byteAtAddrPlus3) {
    return (((int) byteAtAddrPlus3) & 0xff) << 24 |
           (((int) byteAtAddrPlus2) & 0xff) << 16 |
           (((int) byteAtAddrPlus1) & 0xff) <<  8 |
           (((int) byteAtAddrPlus0) & 0xff);
  }
  
  @Override
  public byte[] integerToBytes (int i) {
    byte[] b = new byte [4];
    b [3] = (byte) (i >>> 24);
    b [2] = (byte) (i >>> 16);
    b [1] = (byte) (i >>> 8);
    b [0] = (byte) (i);
    return b;
  }
  
  @Override
  protected byte[] get (int address, int length) throws InvalidAddressException {
    if (address < 0 || address+length-1 >= mem.length)
      throw new InvalidAddressException ();
    byte[] value = new byte[length];
    for (int i=0; i<length; i++)
      value[i] =  mem[address+i];
    return value;
  }
  
  @Override
  protected void set (int address, byte[] value) throws InvalidAddressException {
    if (address < 0 || address+value.length-1 >= mem.length)
      throw new InvalidAddressException ();
    for (int i=0; i<value.length; i++)
      mem[address+i] = value[i];
  }
  
  @Override
  public int length () {
    return mem.length;
  }
}
