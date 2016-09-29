// $ANTLR 3.5.1 /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g 2014-12-12 15:41:27

package grammar;

import isa.Memory;
import isa.MemoryCell;
import isa.Instruction;
import isa.Datum;
import arch.sm213.isa.Assembler;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class AsmSM213Parser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "Character", "Comment", "CommentZ", 
		"Digit", "Hex", "HexDigit", "Identifier", "NegativeDecimal", "NewLine", 
		"Register", "Registerunsigned", "UnsignedDecimal", "WS", "'$'", "'('", 
		"')'", "'*'", "','", "'.address'", "'.data'", "'.long'", "'.pos'", "':'", 
		"'add'", "'and'", "'beq'", "'bgt'", "'br'", "'dec'", "'deca'", "'gpc'", 
		"'halt'", "'inc'", "'inca'", "'j'", "'ld'", "'mov'", "'nop'", "'not'", 
		"'shl'", "'shr'", "'st'"
	};
	public static final int EOF=-1;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int Character=4;
	public static final int Comment=5;
	public static final int CommentZ=6;
	public static final int Digit=7;
	public static final int Hex=8;
	public static final int HexDigit=9;
	public static final int Identifier=10;
	public static final int NegativeDecimal=11;
	public static final int NewLine=12;
	public static final int Register=13;
	public static final int Registerunsigned=14;
	public static final int UnsignedDecimal=15;
	public static final int WS=16;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public AsmSM213Parser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public AsmSM213Parser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return AsmSM213Parser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g"; }


	public enum LineType { INSTRUCTION, DATA, NULL };
	Memory memory;
	LineType lineType;
	int pc;
	int opCode;
	int[] op = new int[4];
	int opLength;
	String label;
	String comment;
	int dataValue;
	int dataCount;
	int pass;

	void init (Memory aMemory, int startingAddress) {
	  memory      = aMemory;
	  pc          = startingAddress;
	  lineType    = LineType.NULL;
	  comment     = "";
	  label       = "";
	}

	public void checkSyntax (Memory aMemory, int startingAddress) throws Assembler.AssemblyException {
	  init (aMemory, startingAddress);
	  pass = 0;
	  try {
	    program ();
	  } catch (RecognitionException e) {
	    throw new Assembler.AssemblyException ("");
	  }
	}

	public void passOne (Memory aMemory, int startingAddress) throws Assembler.AssemblyException {
	  init (aMemory, startingAddress);
	  pass = 1;
	  try {
	    program ();
	  } catch (RecognitionException e) {
	    throw new Assembler.AssemblyException ("");
	  }
	}

	public void passTwo (Memory aMemory, int startingAddress) throws Assembler.AssemblyException {
	  init (aMemory, startingAddress);
	  pass = 2;
	  try {
	    program ();
	  } catch (RecognitionException e) {
	    throw new Assembler.AssemblyException ("");
	  }
	}

	@Override
	public void emitErrorMessage(String msg) {
	  throw new Assembler.AssemblyException (msg);
	}

	int getLabelValue (String label) {
	  Integer value = memory.getLabelMap ().getAddress (label);
	  if (value==null) {
	    if (pass==1)
	      value = pc;
	    else
	      emitErrorMessage (java.lang.String.format ("Label not found: %s at address %d", label, pc));
	  }
	  return value.intValue ();
	}

	void writeLine () throws RecognitionException {
	  MemoryCell cell = null;
	  switch (lineType) {
	    case INSTRUCTION:
	      try {
	        cell = Instruction.valueOf (memory, pc, opCode, op, label, comment);
	        if (cell==null)
	          throw new RecognitionException ();
	        if (pass==1 && !label.trim ().equals ("")) 
	          memory.addLabelOnly (cell);
	        else if (pass==2)
	          memory.add (cell);
	        label = "";
	        comment = "";
	        pc += cell.length ();
	      } catch (IndexOutOfBoundsException e) {
	        throw new RecognitionException ();
	      }
	      break;
	    case DATA:
	      for (int i=0; i<dataCount; i++) {
	        cell = Datum.valueOf (memory, pc, dataValue, label, comment);
	        if (cell==null)
	          throw new RecognitionException ();
	        if (pass==1 && !label.trim ().equals (""))
	          memory.addLabelOnly (cell);
	        else if (pass==2)
	          memory.add (cell);
	        label = "";
	        comment = "";
	        pc += 4;
	      }
	      label = "";
	      comment = "";
	      break;
	    default:
	  }
	  lineType = LineType.NULL;
	  op[0]=0;
	  op[1]=0;
	  op[2]=0;
	  op[3]=0;
	}



	// $ANTLR start "program"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:142:1: program : ( line )* ( lineZ )? ;
	public final void program() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:142:9: ( ( line )* ( lineZ )? )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:142:11: ( line )* ( lineZ )?
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:142:11: ( line )*
			loop1:
			while (true) {
				int alt1=2;
				alt1 = dfa1.predict(input);
				switch (alt1) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:142:11: line
					{
					pushFollow(FOLLOW_line_in_program46);
					line();
					state._fsp--;

					}
					break;

				default :
					break loop1;
				}
			}

			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:142:17: ( lineZ )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==Identifier||(LA2_0 >= 22 && LA2_0 <= 25)||(LA2_0 >= 27 && LA2_0 <= 45)) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:142:17: lineZ
					{
					pushFollow(FOLLOW_lineZ_in_program49);
					lineZ();
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "program"



	// $ANTLR start "line"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:1: line : ( labelDeclaration )? ( instruction | directive )? ( NewLine | ( Comment ) ) ;
	public final void line() throws RecognitionException {
		Token Comment1=null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:6: ( ( labelDeclaration )? ( instruction | directive )? ( NewLine | ( Comment ) ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:8: ( labelDeclaration )? ( instruction | directive )? ( NewLine | ( Comment ) )
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:8: ( labelDeclaration )?
			int alt3=2;
			switch ( input.LA(1) ) {
				case Identifier:
					{
					alt3=1;
					}
					break;
				case 39:
					{
					int LA3_2 = input.LA(2);
					if ( (LA3_2==26) ) {
						alt3=1;
					}
					}
					break;
				case 45:
					{
					int LA3_3 = input.LA(2);
					if ( (LA3_3==26) ) {
						alt3=1;
					}
					}
					break;
				case 42:
					{
					int LA3_4 = input.LA(2);
					if ( (LA3_4==26) ) {
						alt3=1;
					}
					}
					break;
				case 36:
					{
					int LA3_5 = input.LA(2);
					if ( (LA3_5==26) ) {
						alt3=1;
					}
					}
					break;
				case 37:
					{
					int LA3_6 = input.LA(2);
					if ( (LA3_6==26) ) {
						alt3=1;
					}
					}
					break;
				case 32:
					{
					int LA3_7 = input.LA(2);
					if ( (LA3_7==26) ) {
						alt3=1;
					}
					}
					break;
				case 33:
					{
					int LA3_8 = input.LA(2);
					if ( (LA3_8==26) ) {
						alt3=1;
					}
					}
					break;
				case 40:
					{
					int LA3_9 = input.LA(2);
					if ( (LA3_9==26) ) {
						alt3=1;
					}
					}
					break;
				case 27:
					{
					int LA3_10 = input.LA(2);
					if ( (LA3_10==26) ) {
						alt3=1;
					}
					}
					break;
				case 28:
					{
					int LA3_11 = input.LA(2);
					if ( (LA3_11==26) ) {
						alt3=1;
					}
					}
					break;
				case 34:
					{
					int LA3_12 = input.LA(2);
					if ( (LA3_12==26) ) {
						alt3=1;
					}
					}
					break;
				case 43:
					{
					int LA3_13 = input.LA(2);
					if ( (LA3_13==26) ) {
						alt3=1;
					}
					}
					break;
				case 44:
					{
					int LA3_14 = input.LA(2);
					if ( (LA3_14==26) ) {
						alt3=1;
					}
					}
					break;
				case 31:
					{
					int LA3_15 = input.LA(2);
					if ( (LA3_15==26) ) {
						alt3=1;
					}
					}
					break;
				case 29:
					{
					int LA3_16 = input.LA(2);
					if ( (LA3_16==26) ) {
						alt3=1;
					}
					}
					break;
				case 30:
					{
					int LA3_17 = input.LA(2);
					if ( (LA3_17==26) ) {
						alt3=1;
					}
					}
					break;
				case 38:
					{
					int LA3_18 = input.LA(2);
					if ( (LA3_18==26) ) {
						alt3=1;
					}
					}
					break;
				case 35:
					{
					int LA3_19 = input.LA(2);
					if ( (LA3_19==26) ) {
						alt3=1;
					}
					}
					break;
				case 41:
					{
					int LA3_20 = input.LA(2);
					if ( (LA3_20==26) ) {
						alt3=1;
					}
					}
					break;
			}
			switch (alt3) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:9: labelDeclaration
					{
					pushFollow(FOLLOW_labelDeclaration_in_line59);
					labelDeclaration();
					state._fsp--;

					}
					break;

			}

			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:28: ( instruction | directive )?
			int alt4=3;
			int LA4_0 = input.LA(1);
			if ( ((LA4_0 >= 27 && LA4_0 <= 45)) ) {
				alt4=1;
			}
			else if ( ((LA4_0 >= 22 && LA4_0 <= 25)) ) {
				alt4=2;
			}
			switch (alt4) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:30: instruction
					{
					pushFollow(FOLLOW_instruction_in_line65);
					instruction();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:44: directive
					{
					pushFollow(FOLLOW_directive_in_line69);
					directive();
					state._fsp--;

					}
					break;

			}

			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:57: ( NewLine | ( Comment ) )
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==NewLine) ) {
				alt5=1;
			}
			else if ( (LA5_0==Comment) ) {
				alt5=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}

			switch (alt5) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:58: NewLine
					{
					match(input,NewLine,FOLLOW_NewLine_in_line75); 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:68: ( Comment )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:68: ( Comment )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:144:69: Comment
					{
					Comment1=(Token)match(input,Comment,FOLLOW_Comment_in_line80); 
					 comment = (Comment1!=null?Comment1.getText():null).substring(1).trim(); 
					}

					}
					break;

			}

			 writeLine (); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "line"



	// $ANTLR start "lineZ"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:1: lineZ : ( labelDeclaration )? ( instruction | directive ) ( EOF | ( CommentZ ) ) ;
	public final void lineZ() throws RecognitionException {
		Token CommentZ2=null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:9: ( ( labelDeclaration )? ( instruction | directive ) ( EOF | ( CommentZ ) ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:11: ( labelDeclaration )? ( instruction | directive ) ( EOF | ( CommentZ ) )
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:11: ( labelDeclaration )?
			int alt6=2;
			switch ( input.LA(1) ) {
				case Identifier:
					{
					alt6=1;
					}
					break;
				case 39:
					{
					int LA6_2 = input.LA(2);
					if ( (LA6_2==26) ) {
						alt6=1;
					}
					}
					break;
				case 45:
					{
					int LA6_3 = input.LA(2);
					if ( (LA6_3==26) ) {
						alt6=1;
					}
					}
					break;
				case 42:
					{
					int LA6_4 = input.LA(2);
					if ( (LA6_4==26) ) {
						alt6=1;
					}
					}
					break;
				case 36:
					{
					int LA6_5 = input.LA(2);
					if ( (LA6_5==26) ) {
						alt6=1;
					}
					}
					break;
				case 37:
					{
					int LA6_6 = input.LA(2);
					if ( (LA6_6==26) ) {
						alt6=1;
					}
					}
					break;
				case 32:
					{
					int LA6_7 = input.LA(2);
					if ( (LA6_7==26) ) {
						alt6=1;
					}
					}
					break;
				case 33:
					{
					int LA6_8 = input.LA(2);
					if ( (LA6_8==26) ) {
						alt6=1;
					}
					}
					break;
				case 40:
					{
					int LA6_9 = input.LA(2);
					if ( (LA6_9==26) ) {
						alt6=1;
					}
					}
					break;
				case 27:
					{
					int LA6_10 = input.LA(2);
					if ( (LA6_10==26) ) {
						alt6=1;
					}
					}
					break;
				case 28:
					{
					int LA6_11 = input.LA(2);
					if ( (LA6_11==26) ) {
						alt6=1;
					}
					}
					break;
				case 34:
					{
					int LA6_12 = input.LA(2);
					if ( (LA6_12==26) ) {
						alt6=1;
					}
					}
					break;
				case 43:
					{
					int LA6_13 = input.LA(2);
					if ( (LA6_13==26) ) {
						alt6=1;
					}
					}
					break;
				case 44:
					{
					int LA6_14 = input.LA(2);
					if ( (LA6_14==26) ) {
						alt6=1;
					}
					}
					break;
				case 31:
					{
					int LA6_15 = input.LA(2);
					if ( (LA6_15==26) ) {
						alt6=1;
					}
					}
					break;
				case 29:
					{
					int LA6_16 = input.LA(2);
					if ( (LA6_16==26) ) {
						alt6=1;
					}
					}
					break;
				case 30:
					{
					int LA6_17 = input.LA(2);
					if ( (LA6_17==26) ) {
						alt6=1;
					}
					}
					break;
				case 38:
					{
					int LA6_18 = input.LA(2);
					if ( (LA6_18==26) ) {
						alt6=1;
					}
					}
					break;
				case 35:
					{
					int LA6_19 = input.LA(2);
					if ( (LA6_19==26) ) {
						alt6=1;
					}
					}
					break;
				case 41:
					{
					int LA6_20 = input.LA(2);
					if ( (LA6_20==26) ) {
						alt6=1;
					}
					}
					break;
			}
			switch (alt6) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:12: labelDeclaration
					{
					pushFollow(FOLLOW_labelDeclaration_in_lineZ97);
					labelDeclaration();
					state._fsp--;

					}
					break;

			}

			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:31: ( instruction | directive )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( ((LA7_0 >= 27 && LA7_0 <= 45)) ) {
				alt7=1;
			}
			else if ( ((LA7_0 >= 22 && LA7_0 <= 25)) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:33: instruction
					{
					pushFollow(FOLLOW_instruction_in_lineZ103);
					instruction();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:47: directive
					{
					pushFollow(FOLLOW_directive_in_lineZ107);
					directive();
					state._fsp--;

					}
					break;

			}

			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:59: ( EOF | ( CommentZ ) )
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==EOF) ) {
				alt8=1;
			}
			else if ( (LA8_0==CommentZ) ) {
				alt8=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:60: EOF
					{
					match(input,EOF,FOLLOW_EOF_in_lineZ112); 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:66: ( CommentZ )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:66: ( CommentZ )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:146:67: CommentZ
					{
					CommentZ2=(Token)match(input,CommentZ,FOLLOW_CommentZ_in_lineZ117); 
					 comment = (CommentZ2!=null?CommentZ2.getText():null).substring(1).trim(); 
					}

					}
					break;

			}

			 writeLine (); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "lineZ"


	public static class labelDeclaration_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "labelDeclaration"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:148:1: labelDeclaration : ( Identifier | operand ) ':' ;
	public final AsmSM213Parser.labelDeclaration_return labelDeclaration() throws RecognitionException {
		AsmSM213Parser.labelDeclaration_return retval = new AsmSM213Parser.labelDeclaration_return();
		retval.start = input.LT(1);

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:149:2: ( ( Identifier | operand ) ':' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:149:4: ( Identifier | operand ) ':'
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:149:4: ( Identifier | operand )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==Identifier) ) {
				alt9=1;
			}
			else if ( ((LA9_0 >= 27 && LA9_0 <= 45)) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:149:5: Identifier
					{
					match(input,Identifier,FOLLOW_Identifier_in_labelDeclaration134); 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:149:18: operand
					{
					pushFollow(FOLLOW_operand_in_labelDeclaration138);
					operand();
					state._fsp--;

					}
					break;

			}

			match(input,26,FOLLOW_26_in_labelDeclaration141); 
			 label = input.toString(retval.start,input.LT(-1)).substring (0, input.toString(retval.start,input.LT(-1)).length()-1) .trim();
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "labelDeclaration"


	public static class label_return extends ParserRuleReturnScope {
		public int value;
	};


	// $ANTLR start "label"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:150:1: label returns [int value] : ( Identifier | operand ) ;
	public final AsmSM213Parser.label_return label() throws RecognitionException {
		AsmSM213Parser.label_return retval = new AsmSM213Parser.label_return();
		retval.start = input.LT(1);

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:151:2: ( ( Identifier | operand ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:151:4: ( Identifier | operand )
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:151:4: ( Identifier | operand )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==Identifier) ) {
				alt10=1;
			}
			else if ( ((LA10_0 >= 27 && LA10_0 <= 45)) ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:151:5: Identifier
					{
					match(input,Identifier,FOLLOW_Identifier_in_label156); 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:151:18: operand
					{
					pushFollow(FOLLOW_operand_in_label160);
					operand();
					state._fsp--;

					}
					break;

			}

			 retval.value = getLabelValue (input.toString(retval.start,input.LT(-1))); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "label"



	// $ANTLR start "instruction"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:154:1: instruction : ( load | store | aluOne | aluTwo | gpc | shift | branch | jump | halt | nop ) ;
	public final void instruction() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:2: ( ( load | store | aluOne | aluTwo | gpc | shift | branch | jump | halt | nop ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:4: ( load | store | aluOne | aluTwo | gpc | shift | branch | jump | halt | nop )
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:4: ( load | store | aluOne | aluTwo | gpc | shift | branch | jump | halt | nop )
			int alt11=10;
			switch ( input.LA(1) ) {
			case 39:
				{
				alt11=1;
				}
				break;
			case 45:
				{
				alt11=2;
				}
				break;
			case 32:
			case 33:
			case 36:
			case 37:
			case 42:
				{
				alt11=3;
				}
				break;
			case 27:
			case 28:
			case 40:
				{
				alt11=4;
				}
				break;
			case 34:
				{
				alt11=5;
				}
				break;
			case 43:
			case 44:
				{
				alt11=6;
				}
				break;
			case 29:
			case 30:
			case 31:
				{
				alt11=7;
				}
				break;
			case 38:
				{
				alt11=8;
				}
				break;
			case 35:
				{
				alt11=9;
				}
				break;
			case 41:
				{
				alt11=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:5: load
					{
					pushFollow(FOLLOW_load_in_instruction175);
					load();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:12: store
					{
					pushFollow(FOLLOW_store_in_instruction179);
					store();
					state._fsp--;

					}
					break;
				case 3 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:20: aluOne
					{
					pushFollow(FOLLOW_aluOne_in_instruction183);
					aluOne();
					state._fsp--;

					}
					break;
				case 4 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:29: aluTwo
					{
					pushFollow(FOLLOW_aluTwo_in_instruction187);
					aluTwo();
					state._fsp--;

					}
					break;
				case 5 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:38: gpc
					{
					pushFollow(FOLLOW_gpc_in_instruction191);
					gpc();
					state._fsp--;

					}
					break;
				case 6 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:44: shift
					{
					pushFollow(FOLLOW_shift_in_instruction195);
					shift();
					state._fsp--;

					}
					break;
				case 7 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:52: branch
					{
					pushFollow(FOLLOW_branch_in_instruction199);
					branch();
					state._fsp--;

					}
					break;
				case 8 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:61: jump
					{
					pushFollow(FOLLOW_jump_in_instruction203);
					jump();
					state._fsp--;

					}
					break;
				case 9 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:68: halt
					{
					pushFollow(FOLLOW_halt_in_instruction207);
					halt();
					state._fsp--;

					}
					break;
				case 10 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:155:75: nop
					{
					pushFollow(FOLLOW_nop_in_instruction211);
					nop();
					state._fsp--;

					}
					break;

			}

			lineType = LineType.INSTRUCTION;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "instruction"



	// $ANTLR start "operand"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:156:1: operand : ( 'ld' | 'st' | 'not' | 'inc' | 'inca' | 'dec' | 'deca' | 'gpc' | 'mov' | 'add' | 'and' | 'shl' | 'shr' | 'br' | 'beq' | 'bgt' | 'j' | 'halt' | 'nop' ) ;
	public final void operand() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:156:9: ( ( 'ld' | 'st' | 'not' | 'inc' | 'inca' | 'dec' | 'deca' | 'gpc' | 'mov' | 'add' | 'and' | 'shl' | 'shr' | 'br' | 'beq' | 'bgt' | 'j' | 'halt' | 'nop' ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
			{
			if ( (input.LA(1) >= 27 && input.LA(1) <= 45) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "operand"



	// $ANTLR start "load"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:158:1: load : 'ld' ( ( literal ',' ld= register ) | ( baseOffset4 | index ) ',' d= register ) ;
	public final void load() throws RecognitionException {
		int ld =0;
		int d =0;
		int literal3 =0;
		ParserRuleReturnScope baseOffset44 =null;
		ParserRuleReturnScope index5 =null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:158:7: ( 'ld' ( ( literal ',' ld= register ) | ( baseOffset4 | index ) ',' d= register ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:158:9: 'ld' ( ( literal ',' ld= register ) | ( baseOffset4 | index ) ',' d= register )
			{
			match(input,39,FOLLOW_39_in_load268); 
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:158:14: ( ( literal ',' ld= register ) | ( baseOffset4 | index ) ',' d= register )
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==17) ) {
				alt13=1;
			}
			else if ( (LA13_0==Hex||LA13_0==UnsignedDecimal||LA13_0==18) ) {
				alt13=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:158:16: ( literal ',' ld= register )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:158:16: ( literal ',' ld= register )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:158:17: literal ',' ld= register
					{
					pushFollow(FOLLOW_literal_in_load273);
					literal3=literal();
					state._fsp--;

					 opCode=0; op[2]=literal3; 
					match(input,21,FOLLOW_21_in_load277); 
					pushFollow(FOLLOW_register_in_load281);
					ld=register();
					state._fsp--;

					 op[0]=ld; 
					}

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:159:10: ( baseOffset4 | index ) ',' d= register
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:159:10: ( baseOffset4 | index )
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( (LA12_0==Hex||LA12_0==UnsignedDecimal) ) {
						alt12=1;
					}
					else if ( (LA12_0==18) ) {
						int LA12_2 = input.LA(2);
						if ( (LA12_2==Register) ) {
							int LA12_3 = input.LA(3);
							if ( (LA12_3==19) ) {
								alt12=1;
							}
							else if ( (LA12_3==21) ) {
								alt12=2;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 12, 3, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 12, 2, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 12, 0, input);
						throw nvae;
					}

					switch (alt12) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:159:11: baseOffset4
							{
							pushFollow(FOLLOW_baseOffset4_in_load299);
							baseOffset44=baseOffset4();
							state._fsp--;

							 opCode=1; op[0]=(baseOffset44!=null?((AsmSM213Parser.baseOffset4_return)baseOffset44).offset:0); op[1]=(baseOffset44!=null?((AsmSM213Parser.baseOffset4_return)baseOffset44).base:0); 
							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:160:10: index
							{
							pushFollow(FOLLOW_index_in_load315);
							index5=index();
							state._fsp--;

							 opCode=2; op[0]=(index5!=null?((AsmSM213Parser.index_return)index5).base:0); op[1]=(index5!=null?((AsmSM213Parser.index_return)index5).index:0); 
							}
							break;

					}

					match(input,21,FOLLOW_21_in_load329); 
					pushFollow(FOLLOW_register_in_load333);
					d=register();
					state._fsp--;

					 op[2] = d; 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "load"



	// $ANTLR start "store"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:162:1: store : 'st' s= register ',' ( baseOffset4 | index ) ;
	public final void store() throws RecognitionException {
		int s =0;
		ParserRuleReturnScope baseOffset46 =null;
		ParserRuleReturnScope index7 =null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:162:7: ( 'st' s= register ',' ( baseOffset4 | index ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:162:9: 'st' s= register ',' ( baseOffset4 | index )
			{
			match(input,45,FOLLOW_45_in_store343); 
			pushFollow(FOLLOW_register_in_store347);
			s=register();
			state._fsp--;

			 op[0]=s; 
			match(input,21,FOLLOW_21_in_store351); 
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:163:4: ( baseOffset4 | index )
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==Hex||LA14_0==UnsignedDecimal) ) {
				alt14=1;
			}
			else if ( (LA14_0==18) ) {
				int LA14_2 = input.LA(2);
				if ( (LA14_2==Register) ) {
					int LA14_3 = input.LA(3);
					if ( (LA14_3==19) ) {
						alt14=1;
					}
					else if ( (LA14_3==21) ) {
						alt14=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 14, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 14, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}

			switch (alt14) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:163:6: baseOffset4
					{
					pushFollow(FOLLOW_baseOffset4_in_store359);
					baseOffset46=baseOffset4();
					state._fsp--;

					 opCode=3; op[1]=(baseOffset46!=null?((AsmSM213Parser.baseOffset4_return)baseOffset46).offset:0); op[2]=(baseOffset46!=null?((AsmSM213Parser.baseOffset4_return)baseOffset46).base:0); 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:164:4: index
					{
					pushFollow(FOLLOW_index_in_store369);
					index7=index();
					state._fsp--;

					 opCode=4; op[1]=(index7!=null?((AsmSM213Parser.index_return)index7).base:0); op[2]=(index7!=null?((AsmSM213Parser.index_return)index7).index:0); 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "store"



	// $ANTLR start "aluOne"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:165:1: aluOne : ( 'not' | 'inc' | 'inca' | 'dec' | 'deca' ) register ;
	public final void aluOne() throws RecognitionException {
		int register8 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:165:8: ( ( 'not' | 'inc' | 'inca' | 'dec' | 'deca' ) register )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:165:10: ( 'not' | 'inc' | 'inca' | 'dec' | 'deca' ) register
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:165:10: ( 'not' | 'inc' | 'inca' | 'dec' | 'deca' )
			int alt15=5;
			switch ( input.LA(1) ) {
			case 42:
				{
				alt15=1;
				}
				break;
			case 36:
				{
				alt15=2;
				}
				break;
			case 37:
				{
				alt15=3;
				}
				break;
			case 32:
				{
				alt15=4;
				}
				break;
			case 33:
				{
				alt15=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:165:12: 'not'
					{
					match(input,42,FOLLOW_42_in_aluOne382); 
					opCode=0x67;
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:165:35: 'inc'
					{
					match(input,36,FOLLOW_36_in_aluOne388); 
					opCode=0x63;
					}
					break;
				case 3 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:165:58: 'inca'
					{
					match(input,37,FOLLOW_37_in_aluOne394); 
					opCode=0x64;
					}
					break;
				case 4 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:165:82: 'dec'
					{
					match(input,32,FOLLOW_32_in_aluOne400); 
					opCode=0x65;
					}
					break;
				case 5 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:165:105: 'deca'
					{
					match(input,33,FOLLOW_33_in_aluOne406); 
					opCode=0x66;
					}
					break;

			}

			pushFollow(FOLLOW_register_in_aluOne411);
			register8=register();
			state._fsp--;

			op[1] = register8;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "aluOne"



	// $ANTLR start "aluTwo"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:166:1: aluTwo : ( 'mov' | 'add' | 'and' ) s= register ',' d= register ;
	public final void aluTwo() throws RecognitionException {
		int s =0;
		int d =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:166:8: ( ( 'mov' | 'add' | 'and' ) s= register ',' d= register )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:166:10: ( 'mov' | 'add' | 'and' ) s= register ',' d= register
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:166:10: ( 'mov' | 'add' | 'and' )
			int alt16=3;
			switch ( input.LA(1) ) {
			case 40:
				{
				alt16=1;
				}
				break;
			case 27:
				{
				alt16=2;
				}
				break;
			case 28:
				{
				alt16=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}
			switch (alt16) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:166:12: 'mov'
					{
					match(input,40,FOLLOW_40_in_aluTwo422); 
					opCode=0x60;
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:166:35: 'add'
					{
					match(input,27,FOLLOW_27_in_aluTwo428); 
					opCode=0x61;
					}
					break;
				case 3 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:166:58: 'and'
					{
					match(input,28,FOLLOW_28_in_aluTwo434); 
					opCode=0x62;
					}
					break;

			}

			pushFollow(FOLLOW_register_in_aluTwo442);
			s=register();
			state._fsp--;

			op[0]=s;
			match(input,21,FOLLOW_21_in_aluTwo446); 
			pushFollow(FOLLOW_register_in_aluTwo450);
			d=register();
			state._fsp--;

			op[1]=d;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "aluTwo"



	// $ANTLR start "gpc"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:167:1: gpc : 'gpc' literal ',' register ;
	public final void gpc() throws RecognitionException {
		int literal9 =0;
		int register10 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:167:9: ( 'gpc' literal ',' register )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:167:11: 'gpc' literal ',' register
			{
			match(input,34,FOLLOW_34_in_gpc463); 
			pushFollow(FOLLOW_literal_in_gpc465);
			literal9=literal();
			state._fsp--;

			match(input,21,FOLLOW_21_in_gpc467); 
			pushFollow(FOLLOW_register_in_gpc469);
			register10=register();
			state._fsp--;

			opCode=0x6f; op[0]=literal9; op[1]=register10;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "gpc"



	// $ANTLR start "shift"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:168:1: shift : ( ( 'shl' | 'shr' ) literal ',' register ) ;
	public final void shift() throws RecognitionException {
		int register11 =0;
		int literal12 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:168:7: ( ( ( 'shl' | 'shr' ) literal ',' register ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:168:9: ( ( 'shl' | 'shr' ) literal ',' register )
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:168:9: ( ( 'shl' | 'shr' ) literal ',' register )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:168:11: ( 'shl' | 'shr' ) literal ',' register
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:168:11: ( 'shl' | 'shr' )
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==43) ) {
				alt17=1;
			}
			else if ( (LA17_0==44) ) {
				alt17=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}

			switch (alt17) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:168:13: 'shl'
					{
					match(input,43,FOLLOW_43_in_shift482); 
					op[1]=1;
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:168:32: 'shr'
					{
					match(input,44,FOLLOW_44_in_shift488); 
					op[1]=-1;
					}
					break;

			}

			pushFollow(FOLLOW_literal_in_shift494);
			literal12=literal();
			state._fsp--;

			match(input,21,FOLLOW_21_in_shift496); 
			pushFollow(FOLLOW_register_in_shift498);
			register11=register();
			state._fsp--;

			}

			opCode=0x7; op[0]=register11; op[1]*=literal12;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "shift"



	// $ANTLR start "branch"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:1: branch : ( ( 'br' ( label | unsigned ) ) | ( ( 'beq' | 'bgt' ) register ',' ( label | unsigned ) ) );
	public final void branch() throws RecognitionException {
		ParserRuleReturnScope label13 =null;
		int unsigned14 =0;
		ParserRuleReturnScope label15 =null;
		int unsigned16 =0;
		int register17 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:9: ( ( 'br' ( label | unsigned ) ) | ( ( 'beq' | 'bgt' ) register ',' ( label | unsigned ) ) )
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==31) ) {
				alt21=1;
			}
			else if ( ((LA21_0 >= 29 && LA21_0 <= 30)) ) {
				alt21=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}

			switch (alt21) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:11: ( 'br' ( label | unsigned ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:11: ( 'br' ( label | unsigned ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:13: 'br' ( label | unsigned )
					{
					match(input,31,FOLLOW_31_in_branch512); 
					opCode=0x8;
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:32: ( label | unsigned )
					int alt18=2;
					int LA18_0 = input.LA(1);
					if ( (LA18_0==Identifier||(LA18_0 >= 27 && LA18_0 <= 45)) ) {
						alt18=1;
					}
					else if ( (LA18_0==Hex||LA18_0==UnsignedDecimal) ) {
						alt18=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 18, 0, input);
						throw nvae;
					}

					switch (alt18) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:33: label
							{
							pushFollow(FOLLOW_label_in_branch517);
							label13=label();
							state._fsp--;

							op[1]=(label13!=null?((AsmSM213Parser.label_return)label13).value:0);
							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:63: unsigned
							{
							pushFollow(FOLLOW_unsigned_in_branch523);
							unsigned14=unsigned();
							state._fsp--;

							op[1]=unsigned14;
							}
							break;

					}

					}

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:101: ( ( 'beq' | 'bgt' ) register ',' ( label | unsigned ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:101: ( ( 'beq' | 'bgt' ) register ',' ( label | unsigned ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:103: ( 'beq' | 'bgt' ) register ',' ( label | unsigned )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:103: ( 'beq' | 'bgt' )
					int alt19=2;
					int LA19_0 = input.LA(1);
					if ( (LA19_0==29) ) {
						alt19=1;
					}
					else if ( (LA19_0==30) ) {
						alt19=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 19, 0, input);
						throw nvae;
					}

					switch (alt19) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:105: 'beq'
							{
							match(input,29,FOLLOW_29_in_branch535); 
							opCode=0x9;
							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:126: 'bgt'
							{
							match(input,30,FOLLOW_30_in_branch540); 
							opCode=0xa;
							}
							break;

					}

					pushFollow(FOLLOW_register_in_branch545);
					register17=register();
					state._fsp--;

					match(input,21,FOLLOW_21_in_branch547); 
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:160: ( label | unsigned )
					int alt20=2;
					int LA20_0 = input.LA(1);
					if ( (LA20_0==Identifier||(LA20_0 >= 27 && LA20_0 <= 45)) ) {
						alt20=1;
					}
					else if ( (LA20_0==Hex||LA20_0==UnsignedDecimal) ) {
						alt20=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 20, 0, input);
						throw nvae;
					}

					switch (alt20) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:161: label
							{
							pushFollow(FOLLOW_label_in_branch550);
							label15=label();
							state._fsp--;

							op[1]=(label15!=null?((AsmSM213Parser.label_return)label15).value:0);
							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:169:191: unsigned
							{
							pushFollow(FOLLOW_unsigned_in_branch556);
							unsigned16=unsigned();
							state._fsp--;

							op[1]=unsigned16;
							}
							break;

					}

					op[0]=register17;
					}

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "branch"



	// $ANTLR start "jump"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:170:1: jump : 'j' ( ( label | unsigned ) |b1= baseOffset2 | ( '*' b2= baseOffset4 ) | ( '*' index ) ) ;
	public final void jump() throws RecognitionException {
		ParserRuleReturnScope b1 =null;
		ParserRuleReturnScope b2 =null;
		ParserRuleReturnScope label18 =null;
		int unsigned19 =0;
		ParserRuleReturnScope index20 =null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:170:6: ( 'j' ( ( label | unsigned ) |b1= baseOffset2 | ( '*' b2= baseOffset4 ) | ( '*' index ) ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:170:8: 'j' ( ( label | unsigned ) |b1= baseOffset2 | ( '*' b2= baseOffset4 ) | ( '*' index ) )
			{
			match(input,38,FOLLOW_38_in_jump569); 
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:170:12: ( ( label | unsigned ) |b1= baseOffset2 | ( '*' b2= baseOffset4 ) | ( '*' index ) )
			int alt23=4;
			switch ( input.LA(1) ) {
			case Identifier:
			case 27:
			case 28:
			case 29:
			case 30:
			case 31:
			case 32:
			case 33:
			case 34:
			case 35:
			case 36:
			case 37:
			case 38:
			case 39:
			case 40:
			case 41:
			case 42:
			case 43:
			case 44:
			case 45:
				{
				alt23=1;
				}
				break;
			case UnsignedDecimal:
				{
				int LA23_2 = input.LA(2);
				if ( (LA23_2==EOF||(LA23_2 >= Comment && LA23_2 <= CommentZ)||LA23_2==NewLine) ) {
					alt23=1;
				}
				else if ( (LA23_2==18) ) {
					alt23=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 23, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case Hex:
				{
				int LA23_3 = input.LA(2);
				if ( (LA23_3==EOF||(LA23_3 >= Comment && LA23_3 <= CommentZ)||LA23_3==NewLine) ) {
					alt23=1;
				}
				else if ( (LA23_3==18) ) {
					alt23=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 23, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 18:
				{
				alt23=2;
				}
				break;
			case 20:
				{
				int LA23_5 = input.LA(2);
				if ( (LA23_5==Hex||LA23_5==UnsignedDecimal) ) {
					alt23=3;
				}
				else if ( (LA23_5==18) ) {
					int LA23_7 = input.LA(3);
					if ( (LA23_7==Register) ) {
						int LA23_8 = input.LA(4);
						if ( (LA23_8==19) ) {
							alt23=3;
						}
						else if ( (LA23_8==21) ) {
							alt23=4;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 23, 8, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 23, 7, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 23, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}
			switch (alt23) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:170:14: ( label | unsigned )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:170:14: ( label | unsigned )
					int alt22=2;
					int LA22_0 = input.LA(1);
					if ( (LA22_0==Identifier||(LA22_0 >= 27 && LA22_0 <= 45)) ) {
						alt22=1;
					}
					else if ( (LA22_0==Hex||LA22_0==UnsignedDecimal) ) {
						alt22=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 22, 0, input);
						throw nvae;
					}

					switch (alt22) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:170:15: label
							{
							pushFollow(FOLLOW_label_in_jump574);
							label18=label();
							state._fsp--;

							opCode=0xb; op[1]=(label18!=null?((AsmSM213Parser.label_return)label18).value:0);
							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:170:57: unsigned
							{
							pushFollow(FOLLOW_unsigned_in_jump580);
							unsigned19=unsigned();
							state._fsp--;

							opCode=0xb; op[1]=unsigned19;
							}
							break;

					}

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:171:9: b1= baseOffset2
					{
					pushFollow(FOLLOW_baseOffset2_in_jump599);
					b1=baseOffset2();
					state._fsp--;

					opCode=0xc; op[0]=(b1!=null?((AsmSM213Parser.baseOffset2_return)b1).base:0); op[1]=(b1!=null?((AsmSM213Parser.baseOffset2_return)b1).offset:0);
					}
					break;
				case 3 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:172:9: ( '*' b2= baseOffset4 )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:172:9: ( '*' b2= baseOffset4 )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:172:11: '*' b2= baseOffset4
					{
					match(input,20,FOLLOW_20_in_jump616); 
					pushFollow(FOLLOW_baseOffset4_in_jump620);
					b2=baseOffset4();
					state._fsp--;

					opCode=0xd; op[0]=(b2!=null?((AsmSM213Parser.baseOffset4_return)b2).base:0); op[1]=(b2!=null?((AsmSM213Parser.baseOffset4_return)b2).offset:0);
					}

					}
					break;
				case 4 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:172:81: ( '*' index )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:172:81: ( '*' index )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:172:82: '*' index
					{
					match(input,20,FOLLOW_20_in_jump628); 
					pushFollow(FOLLOW_index_in_jump630);
					index20=index();
					state._fsp--;

					opCode=0xe; op[0]=(index20!=null?((AsmSM213Parser.index_return)index20).base:0); op[1]=(index20!=null?((AsmSM213Parser.index_return)index20).index:0);
					}

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "jump"



	// $ANTLR start "halt"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:173:1: halt : 'halt' ;
	public final void halt() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:173:6: ( 'halt' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:173:8: 'halt'
			{
			match(input,35,FOLLOW_35_in_halt641); 
			opCode=0xf0;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "halt"



	// $ANTLR start "nop"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:174:1: nop : 'nop' ;
	public final void nop() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:174:5: ( 'nop' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:174:7: 'nop'
			{
			match(input,41,FOLLOW_41_in_nop650); 
			opCode=0xff;
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "nop"



	// $ANTLR start "literal"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:177:1: literal returns [int value] : '$' ( signed | label ) ;
	public final int literal() throws RecognitionException {
		int value = 0;


		int signed21 =0;
		ParserRuleReturnScope label22 =null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:178:2: ( '$' ( signed | label ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:178:4: '$' ( signed | label )
			{
			match(input,17,FOLLOW_17_in_literal666); 
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:178:8: ( signed | label )
			int alt24=2;
			int LA24_0 = input.LA(1);
			if ( (LA24_0==Hex||LA24_0==NegativeDecimal||LA24_0==UnsignedDecimal) ) {
				alt24=1;
			}
			else if ( (LA24_0==Identifier||(LA24_0 >= 27 && LA24_0 <= 45)) ) {
				alt24=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}

			switch (alt24) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:178:9: signed
					{
					pushFollow(FOLLOW_signed_in_literal669);
					signed21=signed();
					state._fsp--;

					 value = signed21; 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:178:46: label
					{
					pushFollow(FOLLOW_label_in_literal675);
					label22=label();
					state._fsp--;

					 value = (label22!=null?((AsmSM213Parser.label_return)label22).value:0); 
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "literal"


	public static class baseOffset2_return extends ParserRuleReturnScope {
		public int offset;
		public int base;
	};


	// $ANTLR start "baseOffset2"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:179:1: baseOffset2 returns [int offset, int base] : ( unsigned )? '(' register ')' ;
	public final AsmSM213Parser.baseOffset2_return baseOffset2() throws RecognitionException {
		AsmSM213Parser.baseOffset2_return retval = new AsmSM213Parser.baseOffset2_return();
		retval.start = input.LT(1);

		int unsigned23 =0;
		int register24 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:180:2: ( ( unsigned )? '(' register ')' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:180:4: ( unsigned )? '(' register ')'
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:180:4: ( unsigned )?
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==Hex||LA25_0==UnsignedDecimal) ) {
				alt25=1;
			}
			switch (alt25) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:180:4: unsigned
					{
					pushFollow(FOLLOW_unsigned_in_baseOffset2690);
					unsigned23=unsigned();
					state._fsp--;

					}
					break;

			}

			match(input,18,FOLLOW_18_in_baseOffset2693); 
			pushFollow(FOLLOW_register_in_baseOffset2695);
			register24=register();
			state._fsp--;

			match(input,19,FOLLOW_19_in_baseOffset2697); 
			 retval.offset =unsigned23; retval.base =register24; if ((retval.offset%2)!=0) emitErrorMessage ("Offset must be a multiple of 2");
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "baseOffset2"


	public static class baseOffset4_return extends ParserRuleReturnScope {
		public int offset;
		public int base;
	};


	// $ANTLR start "baseOffset4"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:181:1: baseOffset4 returns [int offset, int base] : ( unsigned )? '(' register ')' ;
	public final AsmSM213Parser.baseOffset4_return baseOffset4() throws RecognitionException {
		AsmSM213Parser.baseOffset4_return retval = new AsmSM213Parser.baseOffset4_return();
		retval.start = input.LT(1);

		int unsigned25 =0;
		int register26 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:182:2: ( ( unsigned )? '(' register ')' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:182:4: ( unsigned )? '(' register ')'
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:182:4: ( unsigned )?
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==Hex||LA26_0==UnsignedDecimal) ) {
				alt26=1;
			}
			switch (alt26) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:182:4: unsigned
					{
					pushFollow(FOLLOW_unsigned_in_baseOffset4711);
					unsigned25=unsigned();
					state._fsp--;

					}
					break;

			}

			match(input,18,FOLLOW_18_in_baseOffset4714); 
			pushFollow(FOLLOW_register_in_baseOffset4716);
			register26=register();
			state._fsp--;

			match(input,19,FOLLOW_19_in_baseOffset4718); 
			 retval.offset =unsigned25; retval.base =register26; if ((retval.offset%4)!=0) emitErrorMessage ("Offset must be a multiple of 4");
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "baseOffset4"


	public static class index_return extends ParserRuleReturnScope {
		public int base;
		public int index;
	};


	// $ANTLR start "index"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:183:1: index returns [int base, int index] : '(' b= register ',' i= register ',' unsignedDecimal ')' ;
	public final AsmSM213Parser.index_return index() throws RecognitionException {
		AsmSM213Parser.index_return retval = new AsmSM213Parser.index_return();
		retval.start = input.LT(1);

		int b =0;
		int i =0;
		int unsignedDecimal27 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:184:2: ( '(' b= register ',' i= register ',' unsignedDecimal ')' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:184:4: '(' b= register ',' i= register ',' unsignedDecimal ')'
			{
			match(input,18,FOLLOW_18_in_index732); 
			pushFollow(FOLLOW_register_in_index736);
			b=register();
			state._fsp--;

			match(input,21,FOLLOW_21_in_index738); 
			pushFollow(FOLLOW_register_in_index742);
			i=register();
			state._fsp--;

			match(input,21,FOLLOW_21_in_index744); 
			pushFollow(FOLLOW_unsignedDecimal_in_index746);
			unsignedDecimal27=unsignedDecimal();
			state._fsp--;

			match(input,19,FOLLOW_19_in_index748); 
			 retval.base = b; retval.index = i; if (unsignedDecimal27!=4) emitErrorMessage ("In index, scale must be 4"); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "index"



	// $ANTLR start "register"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:185:1: register returns [int value] : Register ;
	public final int register() throws RecognitionException {
		int value = 0;


		Token Register28=null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:186:2: ( Register )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:186:4: Register
			{
			Register28=(Token)match(input,Register,FOLLOW_Register_in_register762); 
			 value = Integer.parseInt ((Register28!=null?Register28.getText():null).substring(1)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "register"



	// $ANTLR start "unsigned"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:187:1: unsigned returns [int value] : ( unsignedDecimal | hex );
	public final int unsigned() throws RecognitionException {
		int value = 0;


		int unsignedDecimal29 =0;
		int hex30 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:188:3: ( unsignedDecimal | hex )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==UnsignedDecimal) ) {
				alt27=1;
			}
			else if ( (LA27_0==Hex) ) {
				alt27=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:188:5: unsignedDecimal
					{
					pushFollow(FOLLOW_unsignedDecimal_in_unsigned777);
					unsignedDecimal29=unsignedDecimal();
					state._fsp--;

					 value =unsignedDecimal29; 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:188:58: hex
					{
					pushFollow(FOLLOW_hex_in_unsigned783);
					hex30=hex();
					state._fsp--;

					 value =hex30; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "unsigned"



	// $ANTLR start "signed"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:189:1: signed returns [int value] : ( signedDecimal | hex );
	public final int signed() throws RecognitionException {
		int value = 0;


		int signedDecimal31 =0;
		int hex32 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:190:3: ( signedDecimal | hex )
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( (LA28_0==NegativeDecimal||LA28_0==UnsignedDecimal) ) {
				alt28=1;
			}
			else if ( (LA28_0==Hex) ) {
				alt28=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}

			switch (alt28) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:190:5: signedDecimal
					{
					pushFollow(FOLLOW_signedDecimal_in_signed798);
					signedDecimal31=signedDecimal();
					state._fsp--;

					 value =signedDecimal31; 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:190:54: hex
					{
					pushFollow(FOLLOW_hex_in_signed804);
					hex32=hex();
					state._fsp--;

					 value =hex32; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "signed"



	// $ANTLR start "hex"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:191:1: hex returns [int value] : Hex ;
	public final int hex() throws RecognitionException {
		int value = 0;


		Token Hex33=null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:192:2: ( Hex )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:192:4: Hex
			{
			Hex33=(Token)match(input,Hex,FOLLOW_Hex_in_hex819); 
			 value =(int)(Long.parseLong((Hex33!=null?Hex33.getText():null).substring(2),16)); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "hex"



	// $ANTLR start "signedDecimal"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:194:1: signedDecimal returns [int value] : ( NegativeDecimal | UnsignedDecimal );
	public final int signedDecimal() throws RecognitionException {
		int value = 0;


		Token NegativeDecimal34=null;
		Token UnsignedDecimal35=null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:195:3: ( NegativeDecimal | UnsignedDecimal )
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( (LA29_0==NegativeDecimal) ) {
				alt29=1;
			}
			else if ( (LA29_0==UnsignedDecimal) ) {
				alt29=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}

			switch (alt29) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:195:5: NegativeDecimal
					{
					NegativeDecimal34=(Token)match(input,NegativeDecimal,FOLLOW_NegativeDecimal_in_signedDecimal838); 
					 value =(int)(Long.parseLong((NegativeDecimal34!=null?NegativeDecimal34.getText():null))); 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:195:80: UnsignedDecimal
					{
					UnsignedDecimal35=(Token)match(input,UnsignedDecimal,FOLLOW_UnsignedDecimal_in_signedDecimal844); 
					 value =(int)(Long.parseLong((UnsignedDecimal35!=null?UnsignedDecimal35.getText():null))); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "signedDecimal"



	// $ANTLR start "unsignedDecimal"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:196:1: unsignedDecimal returns [int value] : UnsignedDecimal ;
	public final int unsignedDecimal() throws RecognitionException {
		int value = 0;


		Token UnsignedDecimal36=null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:197:3: ( UnsignedDecimal )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:197:5: UnsignedDecimal
			{
			UnsignedDecimal36=(Token)match(input,UnsignedDecimal,FOLLOW_UnsignedDecimal_in_unsignedDecimal859); 
			 value =(int)(Long.parseLong((UnsignedDecimal36!=null?UnsignedDecimal36.getText():null))); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "unsignedDecimal"



	// $ANTLR start "directive"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:199:1: directive : ( address | data );
	public final void directive() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:200:2: ( address | data )
			int alt30=2;
			int LA30_0 = input.LA(1);
			if ( (LA30_0==22||LA30_0==25) ) {
				alt30=1;
			}
			else if ( ((LA30_0 >= 23 && LA30_0 <= 24)) ) {
				alt30=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 30, 0, input);
				throw nvae;
			}

			switch (alt30) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:200:4: address
					{
					pushFollow(FOLLOW_address_in_directive876);
					address();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:200:14: data
					{
					pushFollow(FOLLOW_data_in_directive880);
					data();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "directive"



	// $ANTLR start "address"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:201:1: address : ( ( '.address' | '.pos' ) hex ) ;
	public final void address() throws RecognitionException {
		int hex37 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:201:9: ( ( ( '.address' | '.pos' ) hex ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:201:11: ( ( '.address' | '.pos' ) hex )
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:201:11: ( ( '.address' | '.pos' ) hex )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:201:12: ( '.address' | '.pos' ) hex
			{
			if ( input.LA(1)==22||input.LA(1)==25 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_hex_in_address896);
			hex37=hex();
			state._fsp--;

			 pc = hex37; 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "address"



	// $ANTLR start "data"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:202:1: data : ( ( '.long' | '.data' ) (value= unsigned | label ) ( ',' count= unsigned )? ) ;
	public final void data() throws RecognitionException {
		int value =0;
		int count =0;
		ParserRuleReturnScope label38 =null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:202:6: ( ( ( '.long' | '.data' ) (value= unsigned | label ) ( ',' count= unsigned )? ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:202:8: ( ( '.long' | '.data' ) (value= unsigned | label ) ( ',' count= unsigned )? )
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:202:8: ( ( '.long' | '.data' ) (value= unsigned | label ) ( ',' count= unsigned )? )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:202:9: ( '.long' | '.data' ) (value= unsigned | label ) ( ',' count= unsigned )?
			{
			if ( (input.LA(1) >= 23 && input.LA(1) <= 24) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:202:29: (value= unsigned | label )
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==Hex||LA31_0==UnsignedDecimal) ) {
				alt31=1;
			}
			else if ( (LA31_0==Identifier||(LA31_0 >= 27 && LA31_0 <= 45)) ) {
				alt31=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}

			switch (alt31) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:202:30: value= unsigned
					{
					pushFollow(FOLLOW_unsigned_in_data918);
					value=unsigned();
					state._fsp--;

					dataValue=value;
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:202:73: label
					{
					pushFollow(FOLLOW_label_in_data924);
					label38=label();
					state._fsp--;

					dataValue=(label38!=null?((AsmSM213Parser.label_return)label38).value:0);
					}
					break;

			}

			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:202:106: ( ',' count= unsigned )?
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==21) ) {
				alt32=1;
			}
			switch (alt32) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:202:107: ',' count= unsigned
					{
					match(input,21,FOLLOW_21_in_data930); 
					pushFollow(FOLLOW_unsigned_in_data934);
					count=unsigned();
					state._fsp--;

					}
					break;

			}

			}

			 lineType = LineType.DATA; dataCount= count>0? count : 1; 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "data"

	// Delegated rules


	protected DFA1 dfa1 = new DFA1(this);
	static final String DFA1_eotS =
		"\u008b\uffff";
	static final String DFA1_eofS =
		"\1\27\22\uffff\2\27\12\uffff\1\27\3\uffff\4\27\1\uffff\4\27\2\uffff\5"+
		"\27\21\uffff\2\27\40\uffff\1\27\2\uffff\5\27\2\uffff\3\27\4\uffff\2\27"+
		"\1\uffff\1\27\1\uffff\1\27\2\uffff\1\27\11\uffff\2\27";
	static final String DFA1_minS =
		"\1\5\1\32\1\10\11\15\3\21\1\10\2\15\1\10\2\5\2\10\2\uffff\1\5\1\10\2\22"+
		"\1\15\1\25\1\5\1\25\2\10\4\5\1\25\4\5\1\15\1\10\5\5\1\10\11\15\3\21\1"+
		"\10\2\15\1\10\2\5\5\25\1\15\1\23\1\10\1\15\12\25\1\10\1\23\2\22\1\15\1"+
		"\10\1\15\1\23\1\25\1\15\2\22\1\15\1\5\2\15\5\5\1\15\1\23\3\5\1\15\1\25"+
		"\1\15\1\23\2\5\1\23\1\5\1\15\1\5\1\17\1\23\1\5\1\15\1\25\1\23\1\25\1\17"+
		"\1\25\1\17\2\23\2\5";
	static final String DFA1_maxS =
		"\1\55\16\32\1\55\2\32\1\55\2\32\1\10\1\55\2\uffff\2\55\2\22\1\15\1\25"+
		"\1\14\1\25\2\55\4\14\1\25\2\14\2\22\1\15\1\22\1\14\4\25\1\22\11\15\3\21"+
		"\1\55\2\15\1\55\2\14\5\25\1\15\1\25\1\22\1\15\12\25\1\55\1\23\2\22\1\15"+
		"\1\17\1\15\1\23\1\25\1\15\2\22\1\15\1\14\2\15\5\14\1\15\1\25\3\14\1\15"+
		"\1\25\1\15\1\25\2\14\1\23\1\14\1\15\1\14\1\17\1\23\1\14\1\15\1\25\1\23"+
		"\1\25\1\17\1\25\1\17\2\23\2\14";
	static final String DFA1_acceptS =
		"\27\uffff\1\2\1\1\162\uffff";
	static final String DFA1_specialS =
		"\u008b\uffff}>";
	static final String[] DFA1_transitionS = {
			"\1\30\4\uffff\1\1\1\uffff\1\30\11\uffff\1\25\2\26\1\25\1\uffff\1\12\1"+
			"\13\1\20\1\21\1\17\1\7\1\10\1\14\1\23\1\5\1\6\1\22\1\2\1\11\1\24\1\4"+
			"\1\15\1\16\1\3",
			"\1\31",
			"\1\34\6\uffff\1\33\1\uffff\1\32\1\35\7\uffff\1\31",
			"\1\36\14\uffff\1\31",
			"\1\37\14\uffff\1\31",
			"\1\37\14\uffff\1\31",
			"\1\37\14\uffff\1\31",
			"\1\37\14\uffff\1\31",
			"\1\37\14\uffff\1\31",
			"\1\40\14\uffff\1\31",
			"\1\40\14\uffff\1\31",
			"\1\40\14\uffff\1\31",
			"\1\41\10\uffff\1\31",
			"\1\42\10\uffff\1\31",
			"\1\42\10\uffff\1\31",
			"\1\46\1\uffff\1\43\4\uffff\1\45\12\uffff\1\31\23\44",
			"\1\47\14\uffff\1\31",
			"\1\47\14\uffff\1\31",
			"\1\53\1\uffff\1\50\4\uffff\1\52\2\uffff\1\54\1\uffff\1\55\5\uffff\1"+
			"\31\23\51",
			"\1\30\1\27\5\uffff\1\30\15\uffff\1\31",
			"\1\30\1\27\5\uffff\1\30\15\uffff\1\31",
			"\1\56",
			"\1\60\1\uffff\1\61\4\uffff\1\57\13\uffff\23\62",
			"",
			"",
			"\1\30\6\uffff\1\30\11\uffff\1\25\2\26\1\25\1\uffff\1\73\1\74\1\101\1"+
			"\102\1\100\1\70\1\71\1\75\1\104\1\66\1\67\1\103\1\63\1\72\1\105\1\65"+
			"\1\76\1\77\1\64",
			"\1\110\1\uffff\1\111\1\106\3\uffff\1\107\13\uffff\23\112",
			"\1\113",
			"\1\113",
			"\1\114",
			"\1\115",
			"\1\30\1\27\5\uffff\1\30",
			"\1\116",
			"\1\121\1\uffff\1\122\1\117\3\uffff\1\120\13\uffff\23\123",
			"\1\126\1\uffff\1\127\1\124\3\uffff\1\125\13\uffff\23\130",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\131",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30\5\uffff\1\54",
			"\1\30\1\27\5\uffff\1\30\5\uffff\1\54",
			"\1\132",
			"\1\134\6\uffff\1\133\2\uffff\1\135",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30\10\uffff\1\136",
			"\1\30\1\27\5\uffff\1\30\10\uffff\1\136",
			"\1\30\1\27\5\uffff\1\30\10\uffff\1\136",
			"\1\30\1\27\5\uffff\1\30\10\uffff\1\136",
			"\1\34\6\uffff\1\33\1\uffff\1\32\1\35",
			"\1\36",
			"\1\37",
			"\1\37",
			"\1\37",
			"\1\37",
			"\1\37",
			"\1\40",
			"\1\40",
			"\1\40",
			"\1\41",
			"\1\42",
			"\1\42",
			"\1\46\1\uffff\1\43\4\uffff\1\45\13\uffff\23\44",
			"\1\47",
			"\1\47",
			"\1\53\1\uffff\1\50\4\uffff\1\52\2\uffff\1\54\1\uffff\1\55\6\uffff\23"+
			"\51",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\137",
			"\1\137",
			"\1\137",
			"\1\137",
			"\1\137",
			"\1\140",
			"\1\141\1\uffff\1\142",
			"\1\144\6\uffff\1\143\2\uffff\1\145",
			"\1\146",
			"\1\147",
			"\1\147",
			"\1\147",
			"\1\147",
			"\1\147",
			"\1\150",
			"\1\150",
			"\1\150",
			"\1\150",
			"\1\150",
			"\1\154\1\uffff\1\151\4\uffff\1\153\13\uffff\23\152",
			"\1\155",
			"\1\156",
			"\1\156",
			"\1\157",
			"\1\161\6\uffff\1\160",
			"\1\162",
			"\1\141",
			"\1\163",
			"\1\164",
			"\1\165",
			"\1\165",
			"\1\166",
			"\1\30\1\27\5\uffff\1\30",
			"\1\167",
			"\1\170",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\171",
			"\1\172\1\uffff\1\173",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\174",
			"\1\175",
			"\1\176",
			"\1\177\1\uffff\1\u0080",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30",
			"\1\172",
			"\1\30\1\27\5\uffff\1\30",
			"\1\u0081",
			"\1\30\1\27\5\uffff\1\30",
			"\1\u0082",
			"\1\177",
			"\1\30\1\27\5\uffff\1\30",
			"\1\u0083",
			"\1\u0084",
			"\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"\1\163",
			"\1\u0088",
			"\1\u0089",
			"\1\u008a",
			"\1\30\1\27\5\uffff\1\30",
			"\1\30\1\27\5\uffff\1\30"
	};

	static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
	static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
	static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
	static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
	static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
	static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
	static final short[][] DFA1_transition;

	static {
		int numStates = DFA1_transitionS.length;
		DFA1_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
		}
	}

	protected class DFA1 extends DFA {

		public DFA1(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 1;
			this.eot = DFA1_eot;
			this.eof = DFA1_eof;
			this.min = DFA1_min;
			this.max = DFA1_max;
			this.accept = DFA1_accept;
			this.special = DFA1_special;
			this.transition = DFA1_transition;
		}
		@Override
		public String getDescription() {
			return "()* loopback of 142:11: ( line )*";
		}
	}

	public static final BitSet FOLLOW_line_in_program46 = new BitSet(new long[]{0x00003FFFFBC01422L});
	public static final BitSet FOLLOW_lineZ_in_program49 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_labelDeclaration_in_line59 = new BitSet(new long[]{0x00003FFFFBC01020L});
	public static final BitSet FOLLOW_instruction_in_line65 = new BitSet(new long[]{0x0000000000001020L});
	public static final BitSet FOLLOW_directive_in_line69 = new BitSet(new long[]{0x0000000000001020L});
	public static final BitSet FOLLOW_NewLine_in_line75 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Comment_in_line80 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_labelDeclaration_in_lineZ97 = new BitSet(new long[]{0x00003FFFFBC00000L});
	public static final BitSet FOLLOW_instruction_in_lineZ103 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_directive_in_lineZ107 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_EOF_in_lineZ112 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CommentZ_in_lineZ117 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_labelDeclaration134 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_operand_in_labelDeclaration138 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_labelDeclaration141 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_label156 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_label160 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_load_in_instruction175 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_store_in_instruction179 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_aluOne_in_instruction183 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_aluTwo_in_instruction187 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_gpc_in_instruction191 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_shift_in_instruction195 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_branch_in_instruction199 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_jump_in_instruction203 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_halt_in_instruction207 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nop_in_instruction211 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_39_in_load268 = new BitSet(new long[]{0x0000000000068100L});
	public static final BitSet FOLLOW_literal_in_load273 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_load277 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_load281 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_baseOffset4_in_load299 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_index_in_load315 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_load329 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_load333 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_45_in_store343 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_store347 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_store351 = new BitSet(new long[]{0x0000000000048100L});
	public static final BitSet FOLLOW_baseOffset4_in_store359 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_index_in_store369 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_42_in_aluOne382 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_36_in_aluOne388 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_37_in_aluOne394 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_32_in_aluOne400 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_33_in_aluOne406 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_aluOne411 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_40_in_aluTwo422 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_27_in_aluTwo428 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_28_in_aluTwo434 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_aluTwo442 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_aluTwo446 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_aluTwo450 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_34_in_gpc463 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_literal_in_gpc465 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_gpc467 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_gpc469 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_43_in_shift482 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_44_in_shift488 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_literal_in_shift494 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_shift496 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_shift498 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_31_in_branch512 = new BitSet(new long[]{0x00003FFFF8008500L});
	public static final BitSet FOLLOW_label_in_branch517 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unsigned_in_branch523 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_29_in_branch535 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_30_in_branch540 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_branch545 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_branch547 = new BitSet(new long[]{0x00003FFFF8008500L});
	public static final BitSet FOLLOW_label_in_branch550 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unsigned_in_branch556 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_38_in_jump569 = new BitSet(new long[]{0x00003FFFF8148500L});
	public static final BitSet FOLLOW_label_in_jump574 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unsigned_in_jump580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_baseOffset2_in_jump599 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_20_in_jump616 = new BitSet(new long[]{0x0000000000048100L});
	public static final BitSet FOLLOW_baseOffset4_in_jump620 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_20_in_jump628 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_index_in_jump630 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_35_in_halt641 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_41_in_nop650 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_literal666 = new BitSet(new long[]{0x00003FFFF8008D00L});
	public static final BitSet FOLLOW_signed_in_literal669 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_label_in_literal675 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unsigned_in_baseOffset2690 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_baseOffset2693 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_baseOffset2695 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_baseOffset2697 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unsigned_in_baseOffset4711 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_baseOffset4714 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_baseOffset4716 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_baseOffset4718 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_18_in_index732 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_index736 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_index738 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_index742 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_21_in_index744 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_unsignedDecimal_in_index746 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_index748 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Register_in_register762 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_unsignedDecimal_in_unsigned777 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_hex_in_unsigned783 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_signedDecimal_in_signed798 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_hex_in_signed804 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Hex_in_hex819 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NegativeDecimal_in_signedDecimal838 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_UnsignedDecimal_in_signedDecimal844 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_UnsignedDecimal_in_unsignedDecimal859 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_address_in_directive876 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_data_in_directive880 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_address888 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_hex_in_address896 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_data907 = new BitSet(new long[]{0x00003FFFF8008500L});
	public static final BitSet FOLLOW_unsigned_in_data918 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_label_in_data924 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_21_in_data930 = new BitSet(new long[]{0x0000000000008100L});
	public static final BitSet FOLLOW_unsigned_in_data934 = new BitSet(new long[]{0x0000000000000002L});
}
