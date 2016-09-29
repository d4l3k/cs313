// $ANTLR 3.5.1 /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g 2015-01-30 16:39:44

package grammar;

import ui.cli.UI;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class CliParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "Character", "Comment", "Decimal", 
		"Digit", "EscapeSpace", "Hex", "HexDigit", "Identifier", "NewLine", "Register", 
		"String", "WS", "'+'", "'-'", "':'", "'='", "'=='", "'a'", "'assert'", 
		"'b'", "'break'", "'clear'", "'cpu'", "'dat'", "'e'", "'e/x'", "'examine'", 
		"'examine/x'", "'g'", "'goto'", "'help'", "'i'", "'i/x'", "'info'", "'info/x'", 
		"'ins'", "'l'", "'load'", "'m'", "'mem'", "'nob'", "'nobreak'", "'not'", 
		"'notrace'", "'prog'", "'quit'", "'r'", "'reg'", "'run'", "'s'", "'step'", 
		"'t'", "'test'", "'trace'", "'w'", "'where'", "'x'"
	};
	public static final int EOF=-1;
	public static final int T__16=16;
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
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int T__54=54;
	public static final int T__55=55;
	public static final int T__56=56;
	public static final int T__57=57;
	public static final int T__58=58;
	public static final int T__59=59;
	public static final int T__60=60;
	public static final int Character=4;
	public static final int Comment=5;
	public static final int Decimal=6;
	public static final int Digit=7;
	public static final int EscapeSpace=8;
	public static final int Hex=9;
	public static final int HexDigit=10;
	public static final int Identifier=11;
	public static final int NewLine=12;
	public static final int Register=13;
	public static final int String=14;
	public static final int WS=15;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public CliParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public CliParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return CliParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g"; }



	public class QuitException extends RuntimeException {}
	public class SyntaxErrorException extends RuntimeException {}

	@Override
	public void reportError (RecognitionException re) {
	  throw new SyntaxErrorException ();
	}

	public interface CommandHandler {
	  public enum MemFormat  {ASM,HEX};
	  public enum MemRegion  {INS, DAT, ALL};
	  public enum InsOper    {REPLACE,INSERT,DELETE};
	  public enum DebugType  {BREAK,TRACE};
	  public enum DebugPoint {INSTRUCTION, MEMORY_READ, MEMORY_WRITE, MEMORY_ACCESS, REGISTER_READ, REGISTER_WRITE, REGISTER_ACCESS};
	  void load              (String filename);
	  void test              (String filename, String bnchArch, String bnchVariant);
	  void run               ();
	  void step              ();
	  void showWhere         ();
	  void gotoPC            (int pc);
	  void examineMem        (int count, MemFormat format, int addr);
	  void examineReg        (int count, int addr);
	  void examineMemAll     (MemFormat format, MemRegion region);
	  void examineRegAll     ();
	  void examineProc       (String state);
	  void assertReg         (int regNum, int value, String desc);
	  void assertMem         (int addr, int value, String desc);
	  void assertDesc        (String desc);
	  void setReg            (int regNum, int value);
	  void setMem            (int addr, int value);
	  void setIns            (int addr, InsOper oper, String value);
	  void debugPoint        (DebugType type, DebugPoint point, boolean isEnabled, int value);
	  void traceProg         (boolean isEnabled);
	  void clearDebugPoints  (DebugType type);
	  void showDebugPoints   (DebugType type);
	  void help              ();
	  int  getRegisterNumber (String registerName);
	  int  getLabelValue     (String label);
	}

	CommandHandler cmd;

	public void setCommandHandler (CommandHandler aCmd) { 
	  cmd = aCmd; 
	}
	boolean dpIsEnabled;



	// $ANTLR start "command"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:1: command : ( load | test | run | step | where | gotoPC | examine | assertCmd | info | set | debug | infoDebug | quit | help ) EOF ;
	public final void command() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:9: ( ( load | test | run | step | where | gotoPC | examine | assertCmd | info | set | debug | infoDebug | quit | help ) EOF )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:11: ( load | test | run | step | where | gotoPC | examine | assertCmd | info | set | debug | infoDebug | quit | help ) EOF
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:11: ( load | test | run | step | where | gotoPC | examine | assertCmd | info | set | debug | infoDebug | quit | help )
			int alt1=14;
			switch ( input.LA(1) ) {
			case 40:
			case 41:
				{
				alt1=1;
				}
				break;
			case 56:
				{
				alt1=2;
				}
				break;
			case 50:
			case 52:
				{
				alt1=3;
				}
				break;
			case 53:
			case 54:
				{
				alt1=4;
				}
				break;
			case 58:
			case 59:
				{
				alt1=5;
				}
				break;
			case 32:
			case 33:
				{
				alt1=6;
				}
				break;
			case 28:
			case 29:
			case 30:
			case 31:
				{
				alt1=7;
				}
				break;
			case 21:
			case 22:
				{
				alt1=8;
				}
				break;
			case 35:
				{
				switch ( input.LA(2) ) {
				case 43:
					{
					int LA1_16 = input.LA(3);
					if ( (LA1_16==EOF) ) {
						alt1=9;
					}
					else if ( ((LA1_16 >= 16 && LA1_16 <= 17)||LA1_16==19) ) {
						alt1=10;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 16, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case 39:
					{
					int LA1_17 = input.LA(3);
					if ( (LA1_17==EOF) ) {
						alt1=9;
					}
					else if ( ((LA1_17 >= 16 && LA1_17 <= 17)||LA1_17==19) ) {
						alt1=10;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 17, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case 27:
					{
					int LA1_18 = input.LA(3);
					if ( (LA1_18==EOF) ) {
						alt1=9;
					}
					else if ( ((LA1_18 >= 16 && LA1_18 <= 17)||LA1_18==19) ) {
						alt1=10;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 18, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case 51:
					{
					int LA1_19 = input.LA(3);
					if ( (LA1_19==EOF) ) {
						alt1=9;
					}
					else if ( ((LA1_19 >= 16 && LA1_19 <= 17)||LA1_19==19) ) {
						alt1=10;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 19, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case 26:
					{
					int LA1_20 = input.LA(3);
					if ( (LA1_20==EOF||LA1_20==Identifier||LA1_20==21||(LA1_20 >= 23 && LA1_20 <= 28)||LA1_20==30||(LA1_20 >= 32 && LA1_20 <= 35)||LA1_20==37||(LA1_20 >= 39 && LA1_20 <= 43)||(LA1_20 >= 48 && LA1_20 <= 60)) ) {
						alt1=9;
					}
					else if ( ((LA1_20 >= 16 && LA1_20 <= 17)||LA1_20==19) ) {
						alt1=10;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 20, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case Decimal:
				case Hex:
				case Identifier:
				case 21:
				case 25:
				case 28:
				case 30:
				case 32:
				case 33:
				case 34:
				case 35:
				case 37:
				case 40:
				case 41:
				case 42:
				case 48:
				case 49:
				case 50:
				case 52:
				case 53:
				case 54:
				case 56:
				case 58:
				case 59:
				case 60:
					{
					alt1=10;
					}
					break;
				case 23:
				case 24:
					{
					int LA1_21 = input.LA(3);
					if ( ((LA1_21 >= 16 && LA1_21 <= 17)||LA1_21==19) ) {
						alt1=10;
					}
					else if ( (LA1_21==EOF) ) {
						alt1=12;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 21, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				case 55:
				case 57:
					{
					int LA1_22 = input.LA(3);
					if ( ((LA1_22 >= 16 && LA1_22 <= 17)||LA1_22==19) ) {
						alt1=10;
					}
					else if ( (LA1_22==EOF) ) {
						alt1=12;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 1, 22, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 9, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 37:
				{
				int LA1_10 = input.LA(2);
				if ( ((LA1_10 >= 26 && LA1_10 <= 27)||LA1_10==39||LA1_10==43||LA1_10==51) ) {
					alt1=9;
				}
				else if ( ((LA1_10 >= 23 && LA1_10 <= 24)||LA1_10==55||LA1_10==57) ) {
					alt1=12;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 10, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 36:
			case 38:
				{
				alt1=9;
				}
				break;
			case Register:
			case 42:
				{
				alt1=10;
				}
				break;
			case 23:
			case 24:
			case 25:
			case 44:
			case 45:
			case 46:
			case 47:
			case 55:
			case 57:
				{
				alt1=11;
				}
				break;
			case 49:
				{
				alt1=13;
				}
				break;
			case 34:
				{
				alt1=14;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:12: load
					{
					pushFollow(FOLLOW_load_in_command41);
					load();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:19: test
					{
					pushFollow(FOLLOW_test_in_command45);
					test();
					state._fsp--;

					}
					break;
				case 3 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:26: run
					{
					pushFollow(FOLLOW_run_in_command49);
					run();
					state._fsp--;

					}
					break;
				case 4 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:32: step
					{
					pushFollow(FOLLOW_step_in_command53);
					step();
					state._fsp--;

					}
					break;
				case 5 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:39: where
					{
					pushFollow(FOLLOW_where_in_command57);
					where();
					state._fsp--;

					}
					break;
				case 6 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:47: gotoPC
					{
					pushFollow(FOLLOW_gotoPC_in_command61);
					gotoPC();
					state._fsp--;

					}
					break;
				case 7 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:56: examine
					{
					pushFollow(FOLLOW_examine_in_command65);
					examine();
					state._fsp--;

					}
					break;
				case 8 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:66: assertCmd
					{
					pushFollow(FOLLOW_assertCmd_in_command69);
					assertCmd();
					state._fsp--;

					}
					break;
				case 9 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:78: info
					{
					pushFollow(FOLLOW_info_in_command73);
					info();
					state._fsp--;

					}
					break;
				case 10 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:85: set
					{
					pushFollow(FOLLOW_set_in_command77);
					set();
					state._fsp--;

					}
					break;
				case 11 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:91: debug
					{
					pushFollow(FOLLOW_debug_in_command81);
					debug();
					state._fsp--;

					}
					break;
				case 12 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:99: infoDebug
					{
					pushFollow(FOLLOW_infoDebug_in_command85);
					infoDebug();
					state._fsp--;

					}
					break;
				case 13 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:111: quit
					{
					pushFollow(FOLLOW_quit_in_command89);
					quit();
					state._fsp--;

					}
					break;
				case 14 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:72:118: help
					{
					pushFollow(FOLLOW_help_in_command93);
					help();
					state._fsp--;

					}
					break;

			}

			match(input,EOF,FOLLOW_EOF_in_command96); 
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
	// $ANTLR end "command"



	// $ANTLR start "keyword"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:74:1: keyword : ( 'a' | 'm' | 'i' | 'l' | 'x' | 'load' | 'test' | 'e' | 'examine' | 'r' | 'run' | 's' | 'step' | 'w' | 'where' | 'g' | 'goto' | 't' | 'trace' | 'b' | 'break' | 'info' | 'help' | 'quit' | 'mem' | 'ins' | 'dat' | 'reg' | 'cpu' | 'clear' | 'prog' ) ;
	public final void keyword() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:74:9: ( ( 'a' | 'm' | 'i' | 'l' | 'x' | 'load' | 'test' | 'e' | 'examine' | 'r' | 'run' | 's' | 'step' | 'w' | 'where' | 'g' | 'goto' | 't' | 'trace' | 'b' | 'break' | 'info' | 'help' | 'quit' | 'mem' | 'ins' | 'dat' | 'reg' | 'cpu' | 'clear' | 'prog' ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:
			{
			if ( input.LA(1)==21||(input.LA(1) >= 23 && input.LA(1) <= 28)||input.LA(1)==30||(input.LA(1) >= 32 && input.LA(1) <= 35)||input.LA(1)==37||(input.LA(1) >= 39 && input.LA(1) <= 43)||(input.LA(1) >= 48 && input.LA(1) <= 60) ) {
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
	// $ANTLR end "keyword"



	// $ANTLR start "load"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:76:1: load : ( 'l' | 'load' ) f= filename ;
	public final void load() throws RecognitionException {
		ParserRuleReturnScope f =null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:76:6: ( ( 'l' | 'load' ) f= filename )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:76:8: ( 'l' | 'load' ) f= filename
			{
			if ( (input.LA(1) >= 40 && input.LA(1) <= 41) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_filename_in_load182);
			f=filename();
			state._fsp--;

			cmd.load ((f!=null?((CliParser.filename_return)f).value:null));
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



	// $ANTLR start "test"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:78:1: test : ( 'test' ) filename (arch= identifier (variant= identifier )? )? ;
	public final void test() throws RecognitionException {
		ParserRuleReturnScope arch =null;
		ParserRuleReturnScope variant =null;
		ParserRuleReturnScope filename1 =null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:78:9: ( ( 'test' ) filename (arch= identifier (variant= identifier )? )? )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:78:17: ( 'test' ) filename (arch= identifier (variant= identifier )? )?
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:78:17: ( 'test' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:78:18: 'test'
			{
			match(input,56,FOLLOW_56_in_test205); 
			}

			pushFollow(FOLLOW_filename_in_test208);
			filename1=filename();
			state._fsp--;

			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:78:35: (arch= identifier (variant= identifier )? )?
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==Identifier||LA3_0==21||(LA3_0 >= 23 && LA3_0 <= 28)||LA3_0==30||(LA3_0 >= 32 && LA3_0 <= 35)||LA3_0==37||(LA3_0 >= 39 && LA3_0 <= 43)||(LA3_0 >= 48 && LA3_0 <= 60)) ) {
				alt3=1;
			}
			switch (alt3) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:78:36: arch= identifier (variant= identifier )?
					{
					pushFollow(FOLLOW_identifier_in_test213);
					arch=identifier();
					state._fsp--;

					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:78:59: (variant= identifier )?
					int alt2=2;
					int LA2_0 = input.LA(1);
					if ( (LA2_0==Identifier||LA2_0==21||(LA2_0 >= 23 && LA2_0 <= 28)||LA2_0==30||(LA2_0 >= 32 && LA2_0 <= 35)||LA2_0==37||(LA2_0 >= 39 && LA2_0 <= 43)||(LA2_0 >= 48 && LA2_0 <= 60)) ) {
						alt2=1;
					}
					switch (alt2) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:78:59: variant= identifier
							{
							pushFollow(FOLLOW_identifier_in_test217);
							variant=identifier();
							state._fsp--;

							}
							break;

					}

					}
					break;

			}

			cmd.test ((filename1!=null?input.toString(filename1.start,filename1.stop):null), (arch!=null?input.toString(arch.start,arch.stop):null), (variant!=null?input.toString(variant.start,variant.stop):null));
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
	// $ANTLR end "test"



	// $ANTLR start "run"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:80:1: run : ( 'r' | 'run' ) ;
	public final void run() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:80:5: ( ( 'r' | 'run' ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:80:7: ( 'r' | 'run' )
			{
			if ( input.LA(1)==50||input.LA(1)==52 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			 cmd.run (); 
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
	// $ANTLR end "run"



	// $ANTLR start "step"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:82:1: step : ( 's' | 'step' ) ;
	public final void step() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:82:6: ( ( 's' | 'step' ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:82:8: ( 's' | 'step' )
			{
			if ( (input.LA(1) >= 53 && input.LA(1) <= 54) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			 cmd.step (); 
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
	// $ANTLR end "step"



	// $ANTLR start "where"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:84:1: where : ( 'w' | 'where' ) ;
	public final void where() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:84:8: ( ( 'w' | 'where' ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:84:10: ( 'w' | 'where' )
			{
			if ( (input.LA(1) >= 58 && input.LA(1) <= 59) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			 cmd.showWhere (); 
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
	// $ANTLR end "where"



	// $ANTLR start "gotoPC"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:86:1: gotoPC : ( 'g' | 'goto' ) pc= address ;
	public final void gotoPC() throws RecognitionException {
		int pc =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:86:8: ( ( 'g' | 'goto' ) pc= address )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:86:10: ( 'g' | 'goto' ) pc= address
			{
			if ( (input.LA(1) >= 32 && input.LA(1) <= 33) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_address_in_gotoPC316);
			pc=address();
			state._fsp--;

			 cmd.gotoPC (pc); 
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
	// $ANTLR end "gotoPC"



	// $ANTLR start "examine"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:88:1: examine : ( ( ( 'e' | 'examine' | ( ( 'e/x' | 'examine/x' ) ) ) ( (addr= address ( ':' count= number )? ) ) ) | ( ( 'e' | 'examine' ) ( ( register ( ':' count= number )? ) ) ) );
	public final void examine() throws RecognitionException {
		int addr =0;
		int count =0;
		int register2 =0;

		CommandHandler.MemFormat format=CommandHandler.MemFormat.ASM;
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:89:9: ( ( ( 'e' | 'examine' | ( ( 'e/x' | 'examine/x' ) ) ) ( (addr= address ( ':' count= number )? ) ) ) | ( ( 'e' | 'examine' ) ( ( register ( ':' count= number )? ) ) ) )
			int alt7=2;
			switch ( input.LA(1) ) {
			case 28:
				{
				int LA7_1 = input.LA(2);
				if ( (LA7_1==Decimal||LA7_1==Hex||LA7_1==Identifier||LA7_1==21||(LA7_1 >= 23 && LA7_1 <= 28)||LA7_1==30||(LA7_1 >= 32 && LA7_1 <= 35)||LA7_1==37||(LA7_1 >= 39 && LA7_1 <= 43)||(LA7_1 >= 48 && LA7_1 <= 60)) ) {
					alt7=1;
				}
				else if ( (LA7_1==Register) ) {
					alt7=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 7, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 30:
				{
				int LA7_2 = input.LA(2);
				if ( (LA7_2==Decimal||LA7_2==Hex||LA7_2==Identifier||LA7_2==21||(LA7_2 >= 23 && LA7_2 <= 28)||LA7_2==30||(LA7_2 >= 32 && LA7_2 <= 35)||LA7_2==37||(LA7_2 >= 39 && LA7_2 <= 43)||(LA7_2 >= 48 && LA7_2 <= 60)) ) {
					alt7=1;
				}
				else if ( (LA7_2==Register) ) {
					alt7=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 7, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 29:
			case 31:
				{
				alt7=1;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}
			switch (alt7) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:89:11: ( ( 'e' | 'examine' | ( ( 'e/x' | 'examine/x' ) ) ) ( (addr= address ( ':' count= number )? ) ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:89:11: ( ( 'e' | 'examine' | ( ( 'e/x' | 'examine/x' ) ) ) ( (addr= address ( ':' count= number )? ) ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:89:12: ( 'e' | 'examine' | ( ( 'e/x' | 'examine/x' ) ) ) ( (addr= address ( ':' count= number )? ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:89:12: ( 'e' | 'examine' | ( ( 'e/x' | 'examine/x' ) ) )
					int alt4=3;
					switch ( input.LA(1) ) {
					case 28:
						{
						alt4=1;
						}
						break;
					case 30:
						{
						alt4=2;
						}
						break;
					case 29:
					case 31:
						{
						alt4=3;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 4, 0, input);
						throw nvae;
					}
					switch (alt4) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:89:13: 'e'
							{
							match(input,28,FOLLOW_28_in_examine354); 
							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:89:17: 'examine'
							{
							match(input,30,FOLLOW_30_in_examine356); 
							}
							break;
						case 3 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:89:27: ( ( 'e/x' | 'examine/x' ) )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:89:27: ( ( 'e/x' | 'examine/x' ) )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:89:28: ( 'e/x' | 'examine/x' )
							{
							if ( input.LA(1)==29||input.LA(1)==31 ) {
								input.consume();
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							format=CommandHandler.MemFormat.HEX;
							}

							}
							break;

					}

					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:90:19: ( (addr= address ( ':' count= number )? ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:90:20: (addr= address ( ':' count= number )? )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:90:20: (addr= address ( ':' count= number )? )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:90:21: addr= address ( ':' count= number )?
					{
					pushFollow(FOLLOW_address_in_examine392);
					addr=address();
					state._fsp--;

					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:90:34: ( ':' count= number )?
					int alt5=2;
					int LA5_0 = input.LA(1);
					if ( (LA5_0==18) ) {
						alt5=1;
					}
					switch (alt5) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:90:35: ':' count= number
							{
							match(input,18,FOLLOW_18_in_examine395); 
							pushFollow(FOLLOW_number_in_examine399);
							count=number();
							state._fsp--;

							}
							break;

					}

					cmd.examineMem (count, format, addr);
					}

					}

					}

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:92:17: ( ( 'e' | 'examine' ) ( ( register ( ':' count= number )? ) ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:92:17: ( ( 'e' | 'examine' ) ( ( register ( ':' count= number )? ) ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:92:18: ( 'e' | 'examine' ) ( ( register ( ':' count= number )? ) )
					{
					if ( input.LA(1)==28||input.LA(1)==30 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:93:19: ( ( register ( ':' count= number )? ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:93:20: ( register ( ':' count= number )? )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:93:20: ( register ( ':' count= number )? )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:93:21: register ( ':' count= number )?
					{
					pushFollow(FOLLOW_register_in_examine456);
					register2=register();
					state._fsp--;

					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:93:30: ( ':' count= number )?
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( (LA6_0==18) ) {
						alt6=1;
					}
					switch (alt6) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:93:31: ':' count= number
							{
							match(input,18,FOLLOW_18_in_examine459); 
							pushFollow(FOLLOW_number_in_examine463);
							count=number();
							state._fsp--;

							}
							break;

					}

					cmd.examineReg (count, register2);
					}

					}

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
	// $ANTLR end "examine"



	// $ANTLR start "assertCmd"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:95:1: assertCmd : ( ( ( 'a' | 'assert' ) addr= address '==' value= address (desc= string )? ) | ( ( 'a' | 'assert' ) register '==' value= address (desc= string )? ) | ( ( 'a' | 'assert' ) desc= string ) );
	public final void assertCmd() throws RecognitionException {
		int addr =0;
		int value =0;
		String desc =null;
		int register3 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:96:2: ( ( ( 'a' | 'assert' ) addr= address '==' value= address (desc= string )? ) | ( ( 'a' | 'assert' ) register '==' value= address (desc= string )? ) | ( ( 'a' | 'assert' ) desc= string ) )
			int alt10=3;
			int LA10_0 = input.LA(1);
			if ( ((LA10_0 >= 21 && LA10_0 <= 22)) ) {
				switch ( input.LA(2) ) {
				case Decimal:
				case Hex:
				case Identifier:
				case 21:
				case 23:
				case 24:
				case 25:
				case 26:
				case 27:
				case 28:
				case 30:
				case 32:
				case 33:
				case 34:
				case 35:
				case 37:
				case 39:
				case 40:
				case 41:
				case 42:
				case 43:
				case 48:
				case 49:
				case 50:
				case 51:
				case 52:
				case 53:
				case 54:
				case 55:
				case 56:
				case 57:
				case 58:
				case 59:
				case 60:
					{
					alt10=1;
					}
					break;
				case Register:
					{
					alt10=2;
					}
					break;
				case String:
					{
					alt10=3;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 10, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:96:4: ( ( 'a' | 'assert' ) addr= address '==' value= address (desc= string )? )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:96:4: ( ( 'a' | 'assert' ) addr= address '==' value= address (desc= string )? )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:96:5: ( 'a' | 'assert' ) addr= address '==' value= address (desc= string )?
					{
					if ( (input.LA(1) >= 21 && input.LA(1) <= 22) ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_address_in_assertCmd492);
					addr=address();
					state._fsp--;

					match(input,20,FOLLOW_20_in_assertCmd494); 
					pushFollow(FOLLOW_address_in_assertCmd498);
					value=address();
					state._fsp--;

					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:96:58: (desc= string )?
					int alt8=2;
					int LA8_0 = input.LA(1);
					if ( (LA8_0==String) ) {
						alt8=1;
					}
					switch (alt8) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:96:58: desc= string
							{
							pushFollow(FOLLOW_string_in_assertCmd502);
							desc=string();
							state._fsp--;

							}
							break;

					}

					}

					cmd.assertMem (addr, value, desc);
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:97:17: ( ( 'a' | 'assert' ) register '==' value= address (desc= string )? )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:97:17: ( ( 'a' | 'assert' ) register '==' value= address (desc= string )? )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:97:18: ( 'a' | 'assert' ) register '==' value= address (desc= string )?
					{
					if ( (input.LA(1) >= 21 && input.LA(1) <= 22) ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_register_in_assertCmd535);
					register3=register();
					state._fsp--;

					match(input,20,FOLLOW_20_in_assertCmd537); 
					pushFollow(FOLLOW_address_in_assertCmd541);
					value=address();
					state._fsp--;

					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:97:67: (desc= string )?
					int alt9=2;
					int LA9_0 = input.LA(1);
					if ( (LA9_0==String) ) {
						alt9=1;
					}
					switch (alt9) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:97:67: desc= string
							{
							pushFollow(FOLLOW_string_in_assertCmd545);
							desc=string();
							state._fsp--;

							}
							break;

					}

					}

					cmd.assertReg (register3, value, desc);
					}
					break;
				case 3 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:98:17: ( ( 'a' | 'assert' ) desc= string )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:98:17: ( ( 'a' | 'assert' ) desc= string )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:98:18: ( 'a' | 'assert' ) desc= string
					{
					if ( (input.LA(1) >= 21 && input.LA(1) <= 22) ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_string_in_assertCmd580);
					desc=string();
					state._fsp--;

					}

					cmd.assertDesc (desc);
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
	// $ANTLR end "assertCmd"



	// $ANTLR start "info"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:99:1: info : ( ( ( 'i' | 'info' | ( ( 'i/x' | 'info/x' ) ) ) ( ( 'mem' ) | ( 'ins' ) | ( 'dat' ) ) ) | ( ( 'i' | 'info' ) ( ( 'reg' ) | ( 'cpu' (ps= identifier )? ) ) ) );
	public final void info() throws RecognitionException {
		ParserRuleReturnScope ps =null;

		CommandHandler.MemFormat format=CommandHandler.MemFormat.ASM;
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:100:9: ( ( ( 'i' | 'info' | ( ( 'i/x' | 'info/x' ) ) ) ( ( 'mem' ) | ( 'ins' ) | ( 'dat' ) ) ) | ( ( 'i' | 'info' ) ( ( 'reg' ) | ( 'cpu' (ps= identifier )? ) ) ) )
			int alt15=2;
			switch ( input.LA(1) ) {
			case 35:
				{
				int LA15_1 = input.LA(2);
				if ( (LA15_1==27||LA15_1==39||LA15_1==43) ) {
					alt15=1;
				}
				else if ( (LA15_1==26||LA15_1==51) ) {
					alt15=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 15, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 37:
				{
				int LA15_2 = input.LA(2);
				if ( (LA15_2==27||LA15_2==39||LA15_2==43) ) {
					alt15=1;
				}
				else if ( (LA15_2==26||LA15_2==51) ) {
					alt15=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 15, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 36:
			case 38:
				{
				alt15=1;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:100:11: ( ( 'i' | 'info' | ( ( 'i/x' | 'info/x' ) ) ) ( ( 'mem' ) | ( 'ins' ) | ( 'dat' ) ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:100:11: ( ( 'i' | 'info' | ( ( 'i/x' | 'info/x' ) ) ) ( ( 'mem' ) | ( 'ins' ) | ( 'dat' ) ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:100:12: ( 'i' | 'info' | ( ( 'i/x' | 'info/x' ) ) ) ( ( 'mem' ) | ( 'ins' ) | ( 'dat' ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:100:12: ( 'i' | 'info' | ( ( 'i/x' | 'info/x' ) ) )
					int alt11=3;
					switch ( input.LA(1) ) {
					case 35:
						{
						alt11=1;
						}
						break;
					case 37:
						{
						alt11=2;
						}
						break;
					case 36:
					case 38:
						{
						alt11=3;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 11, 0, input);
						throw nvae;
					}
					switch (alt11) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:100:13: 'i'
							{
							match(input,35,FOLLOW_35_in_info615); 
							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:100:17: 'info'
							{
							match(input,37,FOLLOW_37_in_info617); 
							}
							break;
						case 3 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:100:24: ( ( 'i/x' | 'info/x' ) )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:100:24: ( ( 'i/x' | 'info/x' ) )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:100:25: ( 'i/x' | 'info/x' )
							{
							if ( input.LA(1)==36||input.LA(1)==38 ) {
								input.consume();
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							format=CommandHandler.MemFormat.HEX;
							}

							}
							break;

					}

					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:101:19: ( ( 'mem' ) | ( 'ins' ) | ( 'dat' ) )
					int alt12=3;
					switch ( input.LA(1) ) {
					case 43:
						{
						alt12=1;
						}
						break;
					case 39:
						{
						alt12=2;
						}
						break;
					case 27:
						{
						alt12=3;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 12, 0, input);
						throw nvae;
					}
					switch (alt12) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:101:20: ( 'mem' )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:101:20: ( 'mem' )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:101:21: 'mem'
							{
							match(input,43,FOLLOW_43_in_info651); 
							cmd.examineMemAll (format, CommandHandler.MemRegion.ALL);
							}

							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:103:20: ( 'ins' )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:103:20: ( 'ins' )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:103:21: 'ins'
							{
							match(input,39,FOLLOW_39_in_info703); 
							cmd.examineMemAll (format, CommandHandler.MemRegion.INS);
							}

							}
							break;
						case 3 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:105:20: ( 'dat' )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:105:20: ( 'dat' )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:105:21: 'dat'
							{
							match(input,27,FOLLOW_27_in_info754); 
							cmd.examineMemAll (format, CommandHandler.MemRegion.DAT);
							}

							}
							break;

					}

					}

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:107:17: ( ( 'i' | 'info' ) ( ( 'reg' ) | ( 'cpu' (ps= identifier )? ) ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:107:17: ( ( 'i' | 'info' ) ( ( 'reg' ) | ( 'cpu' (ps= identifier )? ) ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:107:18: ( 'i' | 'info' ) ( ( 'reg' ) | ( 'cpu' (ps= identifier )? ) )
					{
					if ( input.LA(1)==35||input.LA(1)==37 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:108:19: ( ( 'reg' ) | ( 'cpu' (ps= identifier )? ) )
					int alt14=2;
					int LA14_0 = input.LA(1);
					if ( (LA14_0==51) ) {
						alt14=1;
					}
					else if ( (LA14_0==26) ) {
						alt14=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 14, 0, input);
						throw nvae;
					}

					switch (alt14) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:108:20: ( 'reg' )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:108:20: ( 'reg' )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:108:21: 'reg'
							{
							match(input,51,FOLLOW_51_in_info830); 
							cmd.examineRegAll ();
							}

							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:109:20: ( 'cpu' (ps= identifier )? )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:109:20: ( 'cpu' (ps= identifier )? )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:109:21: 'cpu' (ps= identifier )?
							{
							match(input,26,FOLLOW_26_in_info857); 
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:109:29: (ps= identifier )?
							int alt13=2;
							int LA13_0 = input.LA(1);
							if ( (LA13_0==Identifier||LA13_0==21||(LA13_0 >= 23 && LA13_0 <= 28)||LA13_0==30||(LA13_0 >= 32 && LA13_0 <= 35)||LA13_0==37||(LA13_0 >= 39 && LA13_0 <= 43)||(LA13_0 >= 48 && LA13_0 <= 60)) ) {
								alt13=1;
							}
							switch (alt13) {
								case 1 :
									// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:109:29: ps= identifier
									{
									pushFollow(FOLLOW_identifier_in_info861);
									ps=identifier();
									state._fsp--;

									}
									break;

							}

							cmd.examineProc ((ps!=null?input.toString(ps.start,ps.stop):null));
							}

							}
							break;

					}

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
	// $ANTLR end "info"



	// $ANTLR start "set"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:110:1: set : ( ( register '=' val= address ) | ( 'm' addr= address '=' val= address ) | ( ( 'i' addr= address ) ( ( '=' instr0= instr ) | ( '+' instr1= instr ) | ( '-' ) ) ) );
	public final void set() throws RecognitionException {
		int val =0;
		int addr =0;
		String instr0 =null;
		String instr1 =null;
		int register4 =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:110:5: ( ( register '=' val= address ) | ( 'm' addr= address '=' val= address ) | ( ( 'i' addr= address ) ( ( '=' instr0= instr ) | ( '+' instr1= instr ) | ( '-' ) ) ) )
			int alt17=3;
			switch ( input.LA(1) ) {
			case Register:
				{
				alt17=1;
				}
				break;
			case 42:
				{
				alt17=2;
				}
				break;
			case 35:
				{
				alt17=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:110:7: ( register '=' val= address )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:110:7: ( register '=' val= address )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:110:8: register '=' val= address
					{
					pushFollow(FOLLOW_register_in_set875);
					register4=register();
					state._fsp--;

					match(input,19,FOLLOW_19_in_set877); 
					pushFollow(FOLLOW_address_in_set881);
					val=address();
					state._fsp--;

					}

					 cmd.setReg (register4, val); 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:112:3: ( 'm' addr= address '=' val= address )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:112:3: ( 'm' addr= address '=' val= address )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:112:4: 'm' addr= address '=' val= address
					{
					match(input,42,FOLLOW_42_in_set895); 
					pushFollow(FOLLOW_address_in_set899);
					addr=address();
					state._fsp--;

					match(input,19,FOLLOW_19_in_set901); 
					pushFollow(FOLLOW_address_in_set905);
					val=address();
					state._fsp--;

					}

					 cmd.setMem (addr, val); 
					}
					break;
				case 3 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:114:3: ( ( 'i' addr= address ) ( ( '=' instr0= instr ) | ( '+' instr1= instr ) | ( '-' ) ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:114:3: ( ( 'i' addr= address ) ( ( '=' instr0= instr ) | ( '+' instr1= instr ) | ( '-' ) ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:114:4: ( 'i' addr= address ) ( ( '=' instr0= instr ) | ( '+' instr1= instr ) | ( '-' ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:114:4: ( 'i' addr= address )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:114:5: 'i' addr= address
					{
					match(input,35,FOLLOW_35_in_set920); 
					pushFollow(FOLLOW_address_in_set924);
					addr=address();
					state._fsp--;

					}

					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:115:19: ( ( '=' instr0= instr ) | ( '+' instr1= instr ) | ( '-' ) )
					int alt16=3;
					switch ( input.LA(1) ) {
					case 19:
						{
						alt16=1;
						}
						break;
					case 16:
						{
						alt16=2;
						}
						break;
					case 17:
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
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:115:20: ( '=' instr0= instr )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:115:20: ( '=' instr0= instr )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:115:21: '=' instr0= instr
							{
							match(input,19,FOLLOW_19_in_set948); 
							pushFollow(FOLLOW_instr_in_set952);
							instr0=instr();
							state._fsp--;

							cmd.setIns (addr, CommandHandler.InsOper.REPLACE, instr0);
							}

							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:117:20: ( '+' instr1= instr )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:117:20: ( '+' instr1= instr )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:117:21: '+' instr1= instr
							{
							match(input,16,FOLLOW_16_in_set1003); 
							pushFollow(FOLLOW_instr_in_set1007);
							instr1=instr();
							state._fsp--;

							cmd.setIns (addr, CommandHandler.InsOper.INSERT, instr1);
							}

							}
							break;
						case 3 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:119:20: ( '-' )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:119:20: ( '-' )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:119:21: '-'
							{
							match(input,17,FOLLOW_17_in_set1058); 
							cmd.setIns (addr, CommandHandler.InsOper.DELETE, "");
							}

							}
							break;

					}

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
	// $ANTLR end "set"



	// $ANTLR start "debug"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:121:1: debug : ( debugPoint | traceProg | clear );
	public final void debug() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:121:9: ( debugPoint | traceProg | clear )
			int alt18=3;
			switch ( input.LA(1) ) {
			case 23:
			case 24:
			case 44:
			case 45:
				{
				alt18=1;
				}
				break;
			case 55:
			case 57:
				{
				int LA18_2 = input.LA(2);
				if ( (LA18_2==21||LA18_2==50||LA18_2==58||LA18_2==60) ) {
					alt18=1;
				}
				else if ( (LA18_2==48) ) {
					alt18=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 18, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 46:
				{
				int LA18_3 = input.LA(2);
				if ( (LA18_3==21||LA18_3==50||LA18_3==58||LA18_3==60) ) {
					alt18=1;
				}
				else if ( (LA18_3==48) ) {
					alt18=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 18, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 47:
				{
				int LA18_4 = input.LA(2);
				if ( (LA18_4==21||LA18_4==50||LA18_4==58||LA18_4==60) ) {
					alt18=1;
				}
				else if ( (LA18_4==48) ) {
					alt18=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 18, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 25:
				{
				alt18=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}
			switch (alt18) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:121:17: debugPoint
					{
					pushFollow(FOLLOW_debugPoint_in_debug1103);
					debugPoint();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:121:30: traceProg
					{
					pushFollow(FOLLOW_traceProg_in_debug1107);
					traceProg();
					state._fsp--;

					}
					break;
				case 3 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:121:42: clear
					{
					pushFollow(FOLLOW_clear_in_debug1111);
					clear();
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
	// $ANTLR end "debug"



	// $ANTLR start "debugPoint"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:122:1: debugPoint : ( ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) ) | ( ( ( 'nob' | 'nobreak' ) ) | ( ( 'not' | 'notrace' ) ) ) ) ( 'x' (a0= address ) | ( ( ( ( 'r' | 'w' | 'a' ) (a1= address ) ) | ( ( 'r' | 'w' | 'a' ) ( register ) ) ) ) ) ;
	public final void debugPoint() throws RecognitionException {
		int a0 =0;
		int a1 =0;
		int register5 =0;

		boolean                   isEnabled=false; 
		                       CommandHandler.DebugType  type=null; 
		                       CommandHandler.DebugPoint point=null; 
		                       int                       value=0;
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:126:9: ( ( ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) ) | ( ( ( 'nob' | 'nobreak' ) ) | ( ( 'not' | 'notrace' ) ) ) ) ( 'x' (a0= address ) | ( ( ( ( 'r' | 'w' | 'a' ) (a1= address ) ) | ( ( 'r' | 'w' | 'a' ) ( register ) ) ) ) ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:126:17: ( ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) ) | ( ( ( 'nob' | 'nobreak' ) ) | ( ( 'not' | 'notrace' ) ) ) ) ( 'x' (a0= address ) | ( ( ( ( 'r' | 'w' | 'a' ) (a1= address ) ) | ( ( 'r' | 'w' | 'a' ) ( register ) ) ) ) )
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:126:17: ( ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) ) | ( ( ( 'nob' | 'nobreak' ) ) | ( ( 'not' | 'notrace' ) ) ) )
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( ((LA21_0 >= 23 && LA21_0 <= 24)||LA21_0==55||LA21_0==57) ) {
				alt21=1;
			}
			else if ( ((LA21_0 >= 44 && LA21_0 <= 47)) ) {
				alt21=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}

			switch (alt21) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:126:18: ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:126:18: ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) )
					int alt19=2;
					int LA19_0 = input.LA(1);
					if ( ((LA19_0 >= 23 && LA19_0 <= 24)) ) {
						alt19=1;
					}
					else if ( (LA19_0==55||LA19_0==57) ) {
						alt19=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 19, 0, input);
						throw nvae;
					}

					switch (alt19) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:126:19: ( ( 'b' | 'break' ) )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:126:19: ( ( 'b' | 'break' ) )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:126:20: ( 'b' | 'break' )
							{
							if ( (input.LA(1) >= 23 && input.LA(1) <= 24) ) {
								input.consume();
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							type=CommandHandler.DebugType.BREAK;
							}

							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:127:18: ( ( 't' | 'trace' ) )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:127:18: ( ( 't' | 'trace' ) )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:127:19: ( 't' | 'trace' )
							{
							if ( input.LA(1)==55||input.LA(1)==57 ) {
								input.consume();
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							type=CommandHandler.DebugType.TRACE;
							}

							}
							break;

					}

					isEnabled=true;
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:128:17: ( ( ( 'nob' | 'nobreak' ) ) | ( ( 'not' | 'notrace' ) ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:128:17: ( ( ( 'nob' | 'nobreak' ) ) | ( ( 'not' | 'notrace' ) ) )
					int alt20=2;
					int LA20_0 = input.LA(1);
					if ( ((LA20_0 >= 44 && LA20_0 <= 45)) ) {
						alt20=1;
					}
					else if ( ((LA20_0 >= 46 && LA20_0 <= 47)) ) {
						alt20=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 20, 0, input);
						throw nvae;
					}

					switch (alt20) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:128:18: ( ( 'nob' | 'nobreak' ) )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:128:18: ( ( 'nob' | 'nobreak' ) )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:128:19: ( 'nob' | 'nobreak' )
							{
							if ( (input.LA(1) >= 44 && input.LA(1) <= 45) ) {
								input.consume();
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							type=CommandHandler.DebugType.BREAK;
							}

							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:129:18: ( ( 'not' | 'notrace' ) )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:129:18: ( ( 'not' | 'notrace' ) )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:129:19: ( 'not' | 'notrace' )
							{
							if ( (input.LA(1) >= 46 && input.LA(1) <= 47) ) {
								input.consume();
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							type=CommandHandler.DebugType.TRACE;
							}

							}
							break;

					}

					isEnabled=false;
					}
					break;

			}

			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:130:17: ( 'x' (a0= address ) | ( ( ( ( 'r' | 'w' | 'a' ) (a1= address ) ) | ( ( 'r' | 'w' | 'a' ) ( register ) ) ) ) )
			int alt25=2;
			int LA25_0 = input.LA(1);
			if ( (LA25_0==60) ) {
				alt25=1;
			}
			else if ( (LA25_0==21||LA25_0==50||LA25_0==58) ) {
				alt25=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}

			switch (alt25) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:130:18: 'x' (a0= address )
					{
					match(input,60,FOLLOW_60_in_debugPoint1272); 
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:130:22: (a0= address )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:130:23: a0= address
					{
					pushFollow(FOLLOW_address_in_debugPoint1277);
					a0=address();
					state._fsp--;

					cmd.debugPoint (type, CommandHandler.DebugPoint.INSTRUCTION, isEnabled, a0);
					}

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:132:17: ( ( ( ( 'r' | 'w' | 'a' ) (a1= address ) ) | ( ( 'r' | 'w' | 'a' ) ( register ) ) ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:132:17: ( ( ( ( 'r' | 'w' | 'a' ) (a1= address ) ) | ( ( 'r' | 'w' | 'a' ) ( register ) ) ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:132:18: ( ( ( 'r' | 'w' | 'a' ) (a1= address ) ) | ( ( 'r' | 'w' | 'a' ) ( register ) ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:132:18: ( ( ( 'r' | 'w' | 'a' ) (a1= address ) ) | ( ( 'r' | 'w' | 'a' ) ( register ) ) )
					int alt24=2;
					switch ( input.LA(1) ) {
					case 50:
						{
						int LA24_1 = input.LA(2);
						if ( (LA24_1==Decimal||LA24_1==Hex||LA24_1==Identifier||LA24_1==21||(LA24_1 >= 23 && LA24_1 <= 28)||LA24_1==30||(LA24_1 >= 32 && LA24_1 <= 35)||LA24_1==37||(LA24_1 >= 39 && LA24_1 <= 43)||(LA24_1 >= 48 && LA24_1 <= 60)) ) {
							alt24=1;
						}
						else if ( (LA24_1==Register) ) {
							alt24=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 24, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case 58:
						{
						int LA24_2 = input.LA(2);
						if ( (LA24_2==Decimal||LA24_2==Hex||LA24_2==Identifier||LA24_2==21||(LA24_2 >= 23 && LA24_2 <= 28)||LA24_2==30||(LA24_2 >= 32 && LA24_2 <= 35)||LA24_2==37||(LA24_2 >= 39 && LA24_2 <= 43)||(LA24_2 >= 48 && LA24_2 <= 60)) ) {
							alt24=1;
						}
						else if ( (LA24_2==Register) ) {
							alt24=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 24, 2, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case 21:
						{
						int LA24_3 = input.LA(2);
						if ( (LA24_3==Decimal||LA24_3==Hex||LA24_3==Identifier||LA24_3==21||(LA24_3 >= 23 && LA24_3 <= 28)||LA24_3==30||(LA24_3 >= 32 && LA24_3 <= 35)||LA24_3==37||(LA24_3 >= 39 && LA24_3 <= 43)||(LA24_3 >= 48 && LA24_3 <= 60)) ) {
							alt24=1;
						}
						else if ( (LA24_3==Register) ) {
							alt24=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 24, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 24, 0, input);
						throw nvae;
					}
					switch (alt24) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:132:19: ( ( 'r' | 'w' | 'a' ) (a1= address ) )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:132:19: ( ( 'r' | 'w' | 'a' ) (a1= address ) )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:132:20: ( 'r' | 'w' | 'a' ) (a1= address )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:132:20: ( 'r' | 'w' | 'a' )
							int alt22=3;
							switch ( input.LA(1) ) {
							case 50:
								{
								alt22=1;
								}
								break;
							case 58:
								{
								alt22=2;
								}
								break;
							case 21:
								{
								alt22=3;
								}
								break;
							default:
								NoViableAltException nvae =
									new NoViableAltException("", 22, 0, input);
								throw nvae;
							}
							switch (alt22) {
								case 1 :
									// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:132:21: 'r'
									{
									match(input,50,FOLLOW_50_in_debugPoint1323); 
									point=CommandHandler.DebugPoint.MEMORY_READ;
									}
									break;
								case 2 :
									// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:133:21: 'w'
									{
									match(input,58,FOLLOW_58_in_debugPoint1350); 
									point=CommandHandler.DebugPoint.MEMORY_WRITE;
									}
									break;
								case 3 :
									// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:134:21: 'a'
									{
									match(input,21,FOLLOW_21_in_debugPoint1377); 
									point=CommandHandler.DebugPoint.MEMORY_ACCESS;
									}
									break;

							}

							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:134:76: (a1= address )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:134:77: a1= address
							{
							pushFollow(FOLLOW_address_in_debugPoint1386);
							a1=address();
							state._fsp--;

							value=a1;
							}

							}

							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:135:19: ( ( 'r' | 'w' | 'a' ) ( register ) )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:135:19: ( ( 'r' | 'w' | 'a' ) ( register ) )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:135:20: ( 'r' | 'w' | 'a' ) ( register )
							{
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:135:20: ( 'r' | 'w' | 'a' )
							int alt23=3;
							switch ( input.LA(1) ) {
							case 50:
								{
								alt23=1;
								}
								break;
							case 58:
								{
								alt23=2;
								}
								break;
							case 21:
								{
								alt23=3;
								}
								break;
							default:
								NoViableAltException nvae =
									new NoViableAltException("", 23, 0, input);
								throw nvae;
							}
							switch (alt23) {
								case 1 :
									// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:135:21: 'r'
									{
									match(input,50,FOLLOW_50_in_debugPoint1416); 
									point=CommandHandler.DebugPoint.REGISTER_READ;
									}
									break;
								case 2 :
									// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:136:21: 'w'
									{
									match(input,58,FOLLOW_58_in_debugPoint1443); 
									point=CommandHandler.DebugPoint.REGISTER_WRITE;
									}
									break;
								case 3 :
									// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:137:21: 'a'
									{
									match(input,21,FOLLOW_21_in_debugPoint1470); 
									point=CommandHandler.DebugPoint.REGISTER_ACCESS;
									}
									break;

							}

							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:137:77: ( register )
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:137:78: register
							{
							pushFollow(FOLLOW_register_in_debugPoint1476);
							register5=register();
							state._fsp--;

							value=register5;
							}

							}

							}
							break;

					}

					cmd.debugPoint (type, point, isEnabled, value);
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
	// $ANTLR end "debugPoint"



	// $ANTLR start "traceProg"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:139:1: traceProg : ( ( ( 't' | 'trace' ) ) | ( 'not' | 'notrace' ) ) 'prog' ;
	public final void traceProg() throws RecognitionException {
		boolean isEnabled=false;
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:140:9: ( ( ( ( 't' | 'trace' ) ) | ( 'not' | 'notrace' ) ) 'prog' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:140:17: ( ( ( 't' | 'trace' ) ) | ( 'not' | 'notrace' ) ) 'prog'
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:140:17: ( ( ( 't' | 'trace' ) ) | ( 'not' | 'notrace' ) )
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==55||LA27_0==57) ) {
				alt27=1;
			}
			else if ( ((LA27_0 >= 46 && LA27_0 <= 47)) ) {
				alt27=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:140:18: ( ( 't' | 'trace' ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:140:18: ( ( 't' | 'trace' ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:140:19: ( 't' | 'trace' )
					{
					if ( input.LA(1)==55||input.LA(1)==57 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					isEnabled=true;
					}

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:140:54: ( 'not' | 'notrace' )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:140:54: ( 'not' | 'notrace' )
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==46) ) {
						alt26=1;
					}
					else if ( (LA26_0==47) ) {
						alt26=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 26, 0, input);
						throw nvae;
					}

					switch (alt26) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:140:55: 'not'
							{
							match(input,46,FOLLOW_46_in_traceProg1548); 
							}
							break;
						case 2 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:140:61: 'notrace'
							{
							match(input,47,FOLLOW_47_in_traceProg1550); 
							isEnabled=false;
							}
							break;

					}

					}
					break;

			}

			match(input,48,FOLLOW_48_in_traceProg1556); 
			cmd.traceProg (isEnabled);
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
	// $ANTLR end "traceProg"



	// $ANTLR start "clear"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:142:1: clear : 'clear' ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) ) ;
	public final void clear() throws RecognitionException {
		CommandHandler.DebugType type=null;
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:143:9: ( 'clear' ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:143:17: 'clear' ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) )
			{
			match(input,25,FOLLOW_25_in_clear1611); 
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:144:17: ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) )
			int alt28=2;
			int LA28_0 = input.LA(1);
			if ( ((LA28_0 >= 23 && LA28_0 <= 24)) ) {
				alt28=1;
			}
			else if ( (LA28_0==55||LA28_0==57) ) {
				alt28=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 28, 0, input);
				throw nvae;
			}

			switch (alt28) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:144:18: ( ( 'b' | 'break' ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:144:18: ( ( 'b' | 'break' ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:144:19: ( 'b' | 'break' )
					{
					if ( (input.LA(1) >= 23 && input.LA(1) <= 24) ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					type=CommandHandler.DebugType.BREAK;
					}

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:145:18: ( ( 't' | 'trace' ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:145:18: ( ( 't' | 'trace' ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:145:19: ( 't' | 'trace' )
					{
					if ( input.LA(1)==55||input.LA(1)==57 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					type=CommandHandler.DebugType.TRACE;
					}

					}
					break;

			}

			cmd.clearDebugPoints (type);
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
	// $ANTLR end "clear"



	// $ANTLR start "infoDebug"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:147:1: infoDebug : ( 'i' | 'info' ) ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) ) ;
	public final void infoDebug() throws RecognitionException {
		CommandHandler.DebugType type=null;
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:148:9: ( ( 'i' | 'info' ) ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:148:17: ( 'i' | 'info' ) ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) )
			{
			if ( input.LA(1)==35||input.LA(1)==37 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:149:10: ( ( ( 'b' | 'break' ) ) | ( ( 't' | 'trace' ) ) )
			int alt29=2;
			int LA29_0 = input.LA(1);
			if ( ((LA29_0 >= 23 && LA29_0 <= 24)) ) {
				alt29=1;
			}
			else if ( (LA29_0==55||LA29_0==57) ) {
				alt29=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}

			switch (alt29) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:149:11: ( ( 'b' | 'break' ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:149:11: ( ( 'b' | 'break' ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:149:12: ( 'b' | 'break' )
					{
					if ( (input.LA(1) >= 23 && input.LA(1) <= 24) ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					type=CommandHandler.DebugType.BREAK;
					}

					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:150:18: ( ( 't' | 'trace' ) )
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:150:18: ( ( 't' | 'trace' ) )
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:150:19: ( 't' | 'trace' )
					{
					if ( input.LA(1)==55||input.LA(1)==57 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					type=CommandHandler.DebugType.TRACE;
					}

					}
					break;

			}

			cmd.showDebugPoints (type);
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
	// $ANTLR end "infoDebug"



	// $ANTLR start "help"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:152:1: help : 'help' ;
	public final void help() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:152:7: ( 'help' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:152:9: 'help'
			{
			match(input,34,FOLLOW_34_in_help1793); 
			cmd.help ();
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
	// $ANTLR end "help"



	// $ANTLR start "quit"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:153:1: quit : 'quit' ;
	public final void quit() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:153:6: ( 'quit' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:153:8: 'quit'
			{
			match(input,49,FOLLOW_49_in_quit1803); 
			throw new QuitException ();
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
	// $ANTLR end "quit"



	// $ANTLR start "address"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:154:1: address returns [int value] : (a= adrAtm |b= adrAtm '+' c= address );
	public final int address() throws RecognitionException {
		int value = 0;


		int a =0;
		int b =0;
		int c =0;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:155:9: (a= adrAtm |b= adrAtm '+' c= address )
			int alt30=2;
			switch ( input.LA(1) ) {
			case Hex:
				{
				int LA30_1 = input.LA(2);
				if ( (LA30_1==EOF||LA30_1==String||(LA30_1 >= 17 && LA30_1 <= 20)) ) {
					alt30=1;
				}
				else if ( (LA30_1==16) ) {
					int LA30_6 = input.LA(3);
					if ( (LA30_6==String) ) {
						alt30=1;
					}
					else if ( (LA30_6==Decimal||LA30_6==Hex||LA30_6==Identifier||LA30_6==21||(LA30_6 >= 23 && LA30_6 <= 28)||LA30_6==30||(LA30_6 >= 32 && LA30_6 <= 35)||LA30_6==37||(LA30_6 >= 39 && LA30_6 <= 43)||(LA30_6 >= 48 && LA30_6 <= 60)) ) {
						alt30=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 30, 6, input);
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
							new NoViableAltException("", 30, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case Decimal:
				{
				int LA30_2 = input.LA(2);
				if ( (LA30_2==EOF||LA30_2==String||(LA30_2 >= 17 && LA30_2 <= 20)) ) {
					alt30=1;
				}
				else if ( (LA30_2==16) ) {
					int LA30_6 = input.LA(3);
					if ( (LA30_6==String) ) {
						alt30=1;
					}
					else if ( (LA30_6==Decimal||LA30_6==Hex||LA30_6==Identifier||LA30_6==21||(LA30_6 >= 23 && LA30_6 <= 28)||LA30_6==30||(LA30_6 >= 32 && LA30_6 <= 35)||LA30_6==37||(LA30_6 >= 39 && LA30_6 <= 43)||(LA30_6 >= 48 && LA30_6 <= 60)) ) {
						alt30=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 30, 6, input);
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
							new NoViableAltException("", 30, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case Identifier:
				{
				int LA30_3 = input.LA(2);
				if ( (LA30_3==EOF||LA30_3==String||(LA30_3 >= 17 && LA30_3 <= 20)) ) {
					alt30=1;
				}
				else if ( (LA30_3==16) ) {
					int LA30_6 = input.LA(3);
					if ( (LA30_6==String) ) {
						alt30=1;
					}
					else if ( (LA30_6==Decimal||LA30_6==Hex||LA30_6==Identifier||LA30_6==21||(LA30_6 >= 23 && LA30_6 <= 28)||LA30_6==30||(LA30_6 >= 32 && LA30_6 <= 35)||LA30_6==37||(LA30_6 >= 39 && LA30_6 <= 43)||(LA30_6 >= 48 && LA30_6 <= 60)) ) {
						alt30=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 30, 6, input);
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
							new NoViableAltException("", 30, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 21:
			case 23:
			case 24:
			case 25:
			case 26:
			case 27:
			case 28:
			case 30:
			case 32:
			case 33:
			case 34:
			case 35:
			case 37:
			case 39:
			case 40:
			case 41:
			case 42:
			case 43:
			case 48:
			case 49:
			case 50:
			case 51:
			case 52:
			case 53:
			case 54:
			case 55:
			case 56:
			case 57:
			case 58:
			case 59:
			case 60:
				{
				int LA30_4 = input.LA(2);
				if ( (LA30_4==EOF||LA30_4==String||(LA30_4 >= 17 && LA30_4 <= 20)) ) {
					alt30=1;
				}
				else if ( (LA30_4==16) ) {
					int LA30_6 = input.LA(3);
					if ( (LA30_6==String) ) {
						alt30=1;
					}
					else if ( (LA30_6==Decimal||LA30_6==Hex||LA30_6==Identifier||LA30_6==21||(LA30_6 >= 23 && LA30_6 <= 28)||LA30_6==30||(LA30_6 >= 32 && LA30_6 <= 35)||LA30_6==37||(LA30_6 >= 39 && LA30_6 <= 43)||(LA30_6 >= 48 && LA30_6 <= 60)) ) {
						alt30=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 30, 6, input);
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
							new NoViableAltException("", 30, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 30, 0, input);
				throw nvae;
			}
			switch (alt30) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:155:15: a= adrAtm
					{
					pushFollow(FOLLOW_adrAtm_in_address1831);
					a=adrAtm();
					state._fsp--;

					value =a;
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:157:15: b= adrAtm '+' c= address
					{
					pushFollow(FOLLOW_adrAtm_in_address1879);
					b=adrAtm();
					state._fsp--;

					match(input,16,FOLLOW_16_in_address1881); 
					pushFollow(FOLLOW_address_in_address1885);
					c=address();
					state._fsp--;

					value =b+c;
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
	// $ANTLR end "address"



	// $ANTLR start "adrAtm"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:159:1: adrAtm returns [int value] : ( number | identifier );
	public final int adrAtm() throws RecognitionException {
		int value = 0;


		int number6 =0;
		ParserRuleReturnScope identifier7 =null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:160:9: ( number | identifier )
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==Decimal||LA31_0==Hex) ) {
				alt31=1;
			}
			else if ( (LA31_0==Identifier||LA31_0==21||(LA31_0 >= 23 && LA31_0 <= 28)||LA31_0==30||(LA31_0 >= 32 && LA31_0 <= 35)||LA31_0==37||(LA31_0 >= 39 && LA31_0 <= 43)||(LA31_0 >= 48 && LA31_0 <= 60)) ) {
				alt31=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}

			switch (alt31) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:160:17: number
					{
					pushFollow(FOLLOW_number_in_adrAtm1937);
					number6=number();
					state._fsp--;

					value =number6;
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:160:50: identifier
					{
					pushFollow(FOLLOW_identifier_in_adrAtm1943);
					identifier7=identifier();
					state._fsp--;

					value =cmd.getLabelValue ((identifier7!=null?input.toString(identifier7.start,identifier7.stop):null));
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
	// $ANTLR end "adrAtm"



	// $ANTLR start "number"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:161:1: number returns [int value] : ( Hex | Decimal );
	public final int number() throws RecognitionException {
		int value = 0;


		Token Hex8=null;
		Token Decimal9=null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:162:2: ( Hex | Decimal )
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==Hex) ) {
				alt32=1;
			}
			else if ( (LA32_0==Decimal) ) {
				alt32=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}

			switch (alt32) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:162:4: Hex
					{
					Hex8=(Token)match(input,Hex,FOLLOW_Hex_in_number1957); 
					 value =(int)(Long.parseLong((Hex8!=null?Hex8.getText():null).substring(2),16)); 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:164:3: Decimal
					{
					Decimal9=(Token)match(input,Decimal,FOLLOW_Decimal_in_number1970); 
					 value =Integer.parseInt((Decimal9!=null?Decimal9.getText():null)); 
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
	// $ANTLR end "number"



	// $ANTLR start "instr"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:166:1: instr returns [String value] : string ;
	public final String instr() throws RecognitionException {
		String value = null;


		String string10 =null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:167:9: ( string )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:167:11: string
			{
			pushFollow(FOLLOW_string_in_instr1995);
			string10=string();
			state._fsp--;

			value =string10;
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
	// $ANTLR end "instr"



	// $ANTLR start "register"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:169:1: register returns [int value] : r= Register ;
	public final int register() throws RecognitionException {
		int value = 0;


		Token r=null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:170:2: (r= Register )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:170:4: r= Register
			{
			r=(Token)match(input,Register,FOLLOW_Register_in_register2035); 
			value = cmd.getRegisterNumber ((r!=null?r.getText():null)!=null?(r!=null?r.getText():null).substring(1,(r!=null?r.getText():null).length()):null);
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


	public static class filename_return extends ParserRuleReturnScope {
		public String value;
	};


	// $ANTLR start "filename"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:171:1: filename returns [String value] : (a= string |b= identifier );
	public final CliParser.filename_return filename() throws RecognitionException {
		CliParser.filename_return retval = new CliParser.filename_return();
		retval.start = input.LT(1);

		String a =null;
		ParserRuleReturnScope b =null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:172:2: (a= string |b= identifier )
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0==String) ) {
				alt33=1;
			}
			else if ( (LA33_0==Identifier||LA33_0==21||(LA33_0 >= 23 && LA33_0 <= 28)||LA33_0==30||(LA33_0 >= 32 && LA33_0 <= 35)||LA33_0==37||(LA33_0 >= 39 && LA33_0 <= 43)||(LA33_0 >= 48 && LA33_0 <= 60)) ) {
				alt33=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 33, 0, input);
				throw nvae;
			}

			switch (alt33) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:172:4: a= string
					{
					pushFollow(FOLLOW_string_in_filename2051);
					a=string();
					state._fsp--;

					retval.value =a;
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:172:34: b= identifier
					{
					pushFollow(FOLLOW_identifier_in_filename2059);
					b=identifier();
					state._fsp--;

					retval.value =(b!=null?input.toString(b.start,b.stop):null);
					}
					break;

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
	// $ANTLR end "filename"


	public static class identifier_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "identifier"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:173:1: identifier : ( Identifier | keyword );
	public final CliParser.identifier_return identifier() throws RecognitionException {
		CliParser.identifier_return retval = new CliParser.identifier_return();
		retval.start = input.LT(1);

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:174:2: ( Identifier | keyword )
			int alt34=2;
			int LA34_0 = input.LA(1);
			if ( (LA34_0==Identifier) ) {
				alt34=1;
			}
			else if ( (LA34_0==21||(LA34_0 >= 23 && LA34_0 <= 28)||LA34_0==30||(LA34_0 >= 32 && LA34_0 <= 35)||LA34_0==37||(LA34_0 >= 39 && LA34_0 <= 43)||(LA34_0 >= 48 && LA34_0 <= 60)) ) {
				alt34=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 34, 0, input);
				throw nvae;
			}

			switch (alt34) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:174:4: Identifier
					{
					match(input,Identifier,FOLLOW_Identifier_in_identifier2069); 
					}
					break;
				case 2 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:174:17: keyword
					{
					pushFollow(FOLLOW_keyword_in_identifier2073);
					keyword();
					state._fsp--;

					}
					break;

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
	// $ANTLR end "identifier"



	// $ANTLR start "string"
	// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:175:1: string returns [String value] : String ;
	public final String string() throws RecognitionException {
		String value = null;


		Token String11=null;

		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:176:9: ( String )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/Cli.g:176:17: String
			{
			String11=(Token)match(input,String,FOLLOW_String_in_string2098); 
			value =(String11!=null?String11.getText():null).substring (1, (String11!=null?String11.getText():null).length()-1);
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
	// $ANTLR end "string"

	// Delegated rules



	public static final BitSet FOLLOW_load_in_command41 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_test_in_command45 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_run_in_command49 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_step_in_command53 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_where_in_command57 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_gotoPC_in_command61 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_examine_in_command65 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_assertCmd_in_command69 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_info_in_command73 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_set_in_command77 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_debug_in_command81 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_infoDebug_in_command85 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_quit_in_command89 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_help_in_command93 = new BitSet(new long[]{0x0000000000000000L});
	public static final BitSet FOLLOW_EOF_in_command96 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_load174 = new BitSet(new long[]{0x1FFF0FAF5FA04800L});
	public static final BitSet FOLLOW_filename_in_load182 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_56_in_test205 = new BitSet(new long[]{0x1FFF0FAF5FA04800L});
	public static final BitSet FOLLOW_filename_in_test208 = new BitSet(new long[]{0x1FFF0FAF5FA00802L});
	public static final BitSet FOLLOW_identifier_in_test213 = new BitSet(new long[]{0x1FFF0FAF5FA00802L});
	public static final BitSet FOLLOW_identifier_in_test217 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_run253 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_step270 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_where290 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_gotoPC308 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_gotoPC316 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_examine354 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_30_in_examine356 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_set_in_examine359 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_examine392 = new BitSet(new long[]{0x0000000000040002L});
	public static final BitSet FOLLOW_18_in_examine395 = new BitSet(new long[]{0x0000000000000240L});
	public static final BitSet FOLLOW_number_in_examine399 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_examine430 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_examine456 = new BitSet(new long[]{0x0000000000040002L});
	public static final BitSet FOLLOW_18_in_examine459 = new BitSet(new long[]{0x0000000000000240L});
	public static final BitSet FOLLOW_number_in_examine463 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_assertCmd482 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_assertCmd492 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_assertCmd494 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_assertCmd498 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_string_in_assertCmd502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_assertCmd527 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_assertCmd535 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_assertCmd537 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_assertCmd541 = new BitSet(new long[]{0x0000000000004002L});
	public static final BitSet FOLLOW_string_in_assertCmd545 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_assertCmd570 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_string_in_assertCmd580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_35_in_info615 = new BitSet(new long[]{0x0000088008000000L});
	public static final BitSet FOLLOW_37_in_info617 = new BitSet(new long[]{0x0000088008000000L});
	public static final BitSet FOLLOW_set_in_info620 = new BitSet(new long[]{0x0000088008000000L});
	public static final BitSet FOLLOW_43_in_info651 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_39_in_info703 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_info754 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_info804 = new BitSet(new long[]{0x0008000004000000L});
	public static final BitSet FOLLOW_51_in_info830 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_info857 = new BitSet(new long[]{0x1FFF0FAF5FA00802L});
	public static final BitSet FOLLOW_identifier_in_info861 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_register_in_set875 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_set877 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_set881 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_42_in_set895 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_set899 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_set901 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_set905 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_35_in_set920 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_set924 = new BitSet(new long[]{0x00000000000B0000L});
	public static final BitSet FOLLOW_19_in_set948 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_instr_in_set952 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_16_in_set1003 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_instr_in_set1007 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_set1058 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_debugPoint_in_debug1103 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_traceProg_in_debug1107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_clear_in_debug1111 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_debugPoint1145 = new BitSet(new long[]{0x1404000000200000L});
	public static final BitSet FOLLOW_set_in_debugPoint1177 = new BitSet(new long[]{0x1404000000200000L});
	public static final BitSet FOLLOW_set_in_debugPoint1213 = new BitSet(new long[]{0x1404000000200000L});
	public static final BitSet FOLLOW_set_in_debugPoint1242 = new BitSet(new long[]{0x1404000000200000L});
	public static final BitSet FOLLOW_60_in_debugPoint1272 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_debugPoint1277 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_50_in_debugPoint1323 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_58_in_debugPoint1350 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_21_in_debugPoint1377 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_debugPoint1386 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_50_in_debugPoint1416 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_58_in_debugPoint1443 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_21_in_debugPoint1470 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_register_in_debugPoint1476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_traceProg1536 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_46_in_traceProg1548 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_47_in_traceProg1550 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_48_in_traceProg1556 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_25_in_clear1611 = new BitSet(new long[]{0x0280000001800000L});
	public static final BitSet FOLLOW_set_in_clear1632 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_clear1660 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_infoDebug1713 = new BitSet(new long[]{0x0280000001800000L});
	public static final BitSet FOLLOW_set_in_infoDebug1730 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_infoDebug1759 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_34_in_help1793 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_49_in_quit1803 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_adrAtm_in_address1831 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_adrAtm_in_address1879 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_address1881 = new BitSet(new long[]{0x1FFF0FAF5FA00A40L});
	public static final BitSet FOLLOW_address_in_address1885 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_number_in_adrAtm1937 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_adrAtm1943 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Hex_in_number1957 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Decimal_in_number1970 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_string_in_instr1995 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Register_in_register2035 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_string_in_filename2051 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_identifier_in_filename2059 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_identifier2069 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_keyword_in_identifier2073 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_String_in_string2098 = new BitSet(new long[]{0x0000000000000002L});
}
