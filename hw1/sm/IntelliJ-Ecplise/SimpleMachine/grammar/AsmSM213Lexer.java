// $ANTLR 3.5.1 /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g 2014-12-12 15:41:27

package grammar;

import arch.sm213.isa.Assembler;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class AsmSM213Lexer extends Lexer {
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

	@Override
	public void emitErrorMessage(String msg) {
	  throw new Assembler.AssemblyException (msg);
	}


	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public AsmSM213Lexer() {} 
	public AsmSM213Lexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public AsmSM213Lexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g"; }

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:14:7: ( '$' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:14:9: '$'
			{
			match('$'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:15:7: ( '(' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:15:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:16:7: ( ')' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:16:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:17:7: ( '*' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:17:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:18:7: ( ',' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:18:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:19:7: ( '.address' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:19:9: '.address'
			{
			match(".address"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:20:7: ( '.data' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:20:9: '.data'
			{
			match(".data"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:21:7: ( '.long' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:21:9: '.long'
			{
			match(".long"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:22:7: ( '.pos' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:22:9: '.pos'
			{
			match(".pos"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:23:7: ( ':' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:23:9: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:24:7: ( 'add' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:24:9: 'add'
			{
			match("add"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:25:7: ( 'and' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:25:9: 'and'
			{
			match("and"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:26:7: ( 'beq' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:26:9: 'beq'
			{
			match("beq"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:27:7: ( 'bgt' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:27:9: 'bgt'
			{
			match("bgt"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:28:7: ( 'br' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:28:9: 'br'
			{
			match("br"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:29:7: ( 'dec' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:29:9: 'dec'
			{
			match("dec"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:30:7: ( 'deca' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:30:9: 'deca'
			{
			match("deca"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:31:7: ( 'gpc' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:31:9: 'gpc'
			{
			match("gpc"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:32:7: ( 'halt' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:32:9: 'halt'
			{
			match("halt"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:33:7: ( 'inc' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:33:9: 'inc'
			{
			match("inc"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:34:7: ( 'inca' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:34:9: 'inca'
			{
			match("inca"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:35:7: ( 'j' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:35:9: 'j'
			{
			match('j'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__38"

	// $ANTLR start "T__39"
	public final void mT__39() throws RecognitionException {
		try {
			int _type = T__39;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:36:7: ( 'ld' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:36:9: 'ld'
			{
			match("ld"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__39"

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:37:7: ( 'mov' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:37:9: 'mov'
			{
			match("mov"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:38:7: ( 'nop' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:38:9: 'nop'
			{
			match("nop"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:39:7: ( 'not' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:39:9: 'not'
			{
			match("not"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:40:7: ( 'shl' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:40:9: 'shl'
			{
			match("shl"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:41:7: ( 'shr' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:41:9: 'shr'
			{
			match("shr"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:42:7: ( 'st' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:42:9: 'st'
			{
			match("st"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__45"

	// $ANTLR start "Register"
	public final void mRegister() throws RecognitionException {
		try {
			int _type = Register;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:206:2: ( ( 'r' | 'R' ) Registerunsigned )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:206:4: ( 'r' | 'R' ) Registerunsigned
			{
			if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			mRegisterunsigned(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Register"

	// $ANTLR start "Identifier"
	public final void mIdentifier() throws RecognitionException {
		try {
			int _type = Identifier;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:208:2: ( Character ( Character | Digit )* )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:208:4: Character ( Character | Digit )*
			{
			mCharacter(); 

			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:208:14: ( Character | Digit )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Identifier"

	// $ANTLR start "UnsignedDecimal"
	public final void mUnsignedDecimal() throws RecognitionException {
		try {
			int _type = UnsignedDecimal;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:210:2: ( ( Digit )+ )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:210:4: ( Digit )+
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:210:4: ( Digit )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UnsignedDecimal"

	// $ANTLR start "NegativeDecimal"
	public final void mNegativeDecimal() throws RecognitionException {
		try {
			int _type = NegativeDecimal;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:212:2: ( '-' ( Digit )+ )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:212:4: '-' ( Digit )+
			{
			match('-'); 
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:212:8: ( Digit )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NegativeDecimal"

	// $ANTLR start "Hex"
	public final void mHex() throws RecognitionException {
		try {
			int _type = Hex;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:213:5: ( '0' ( 'x' | 'X' ) ( HexDigit )+ )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:213:7: '0' ( 'x' | 'X' ) ( HexDigit )+
			{
			match('0'); 
			if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:213:21: ( HexDigit )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '0' && LA4_0 <= '9')||(LA4_0 >= 'A' && LA4_0 <= 'F')||(LA4_0 >= 'a' && LA4_0 <= 'f')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Hex"

	// $ANTLR start "Registerunsigned"
	public final void mRegisterunsigned() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:217:2: ( ( '0' .. '7' ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Registerunsigned"

	// $ANTLR start "HexDigit"
	public final void mHexDigit() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:219:9: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HexDigit"

	// $ANTLR start "Digit"
	public final void mDigit() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:221:7: ( ( '0' .. '9' ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Digit"

	// $ANTLR start "Character"
	public final void mCharacter() throws RecognitionException {
		try {
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:224:2: ( 'A' .. 'Z' | 'a' .. 'z' | '_' )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Character"

	// $ANTLR start "Comment"
	public final void mComment() throws RecognitionException {
		try {
			int _type = Comment;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:224:9: ( '#' ( (~ ( '\\n' | '\\r' ) )* NewLine ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:224:11: '#' ( (~ ( '\\n' | '\\r' ) )* NewLine )
			{
			match('#'); 
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:224:15: ( (~ ( '\\n' | '\\r' ) )* NewLine )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:224:17: (~ ( '\\n' | '\\r' ) )* NewLine
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:224:17: (~ ( '\\n' | '\\r' ) )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\uFFFF')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop5;
				}
			}

			mNewLine(); 

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Comment"

	// $ANTLR start "CommentZ"
	public final void mCommentZ() throws RecognitionException {
		try {
			int _type = CommentZ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:225:9: ( '#' ( (~ ( '\\n' | '\\r' ) )* EOF ) )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:225:11: '#' ( (~ ( '\\n' | '\\r' ) )* EOF )
			{
			match('#'); 
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:225:15: ( (~ ( '\\n' | '\\r' ) )* EOF )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:225:17: (~ ( '\\n' | '\\r' ) )* EOF
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:225:17: (~ ( '\\n' | '\\r' ) )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( ((LA6_0 >= '\u0000' && LA6_0 <= '\t')||(LA6_0 >= '\u000B' && LA6_0 <= '\f')||(LA6_0 >= '\u000E' && LA6_0 <= '\uFFFF')) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop6;
				}
			}

			match(EOF); 

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CommentZ"

	// $ANTLR start "NewLine"
	public final void mNewLine() throws RecognitionException {
		try {
			int _type = NewLine;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:226:9: ( ( ( '\\r' )? '\\n' )+ )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:226:11: ( ( '\\r' )? '\\n' )+
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:226:11: ( ( '\\r' )? '\\n' )+
			int cnt8=0;
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0=='\n'||LA8_0=='\r') ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:226:12: ( '\\r' )? '\\n'
					{
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:226:12: ( '\\r' )?
					int alt7=2;
					int LA7_0 = input.LA(1);
					if ( (LA7_0=='\r') ) {
						alt7=1;
					}
					switch (alt7) {
						case 1 :
							// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:226:12: '\\r'
							{
							match('\r'); 
							}
							break;

					}

					match('\n'); 
					}
					break;

				default :
					if ( cnt8 >= 1 ) break loop8;
					EarlyExitException eee = new EarlyExitException(8, input);
					throw eee;
				}
				cnt8++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NewLine"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:227:6: ( ( ' ' | '\\t' )+ )
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:227:11: ( ' ' | '\\t' )+
			{
			// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:227:11: ( ' ' | '\\t' )+
			int cnt9=0;
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0=='\t'||LA9_0==' ') ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:
					{
					if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt9 >= 1 ) break loop9;
					EarlyExitException eee = new EarlyExitException(9, input);
					throw eee;
				}
				cnt9++;
			}

			 _channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:8: ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | Register | Identifier | UnsignedDecimal | NegativeDecimal | Hex | Comment | CommentZ | NewLine | WS )
		int alt10=38;
		alt10 = dfa10.predict(input);
		switch (alt10) {
			case 1 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:10: T__17
				{
				mT__17(); 

				}
				break;
			case 2 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:16: T__18
				{
				mT__18(); 

				}
				break;
			case 3 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:22: T__19
				{
				mT__19(); 

				}
				break;
			case 4 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:28: T__20
				{
				mT__20(); 

				}
				break;
			case 5 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:34: T__21
				{
				mT__21(); 

				}
				break;
			case 6 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:40: T__22
				{
				mT__22(); 

				}
				break;
			case 7 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:46: T__23
				{
				mT__23(); 

				}
				break;
			case 8 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:52: T__24
				{
				mT__24(); 

				}
				break;
			case 9 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:58: T__25
				{
				mT__25(); 

				}
				break;
			case 10 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:64: T__26
				{
				mT__26(); 

				}
				break;
			case 11 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:70: T__27
				{
				mT__27(); 

				}
				break;
			case 12 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:76: T__28
				{
				mT__28(); 

				}
				break;
			case 13 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:82: T__29
				{
				mT__29(); 

				}
				break;
			case 14 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:88: T__30
				{
				mT__30(); 

				}
				break;
			case 15 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:94: T__31
				{
				mT__31(); 

				}
				break;
			case 16 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:100: T__32
				{
				mT__32(); 

				}
				break;
			case 17 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:106: T__33
				{
				mT__33(); 

				}
				break;
			case 18 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:112: T__34
				{
				mT__34(); 

				}
				break;
			case 19 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:118: T__35
				{
				mT__35(); 

				}
				break;
			case 20 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:124: T__36
				{
				mT__36(); 

				}
				break;
			case 21 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:130: T__37
				{
				mT__37(); 

				}
				break;
			case 22 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:136: T__38
				{
				mT__38(); 

				}
				break;
			case 23 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:142: T__39
				{
				mT__39(); 

				}
				break;
			case 24 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:148: T__40
				{
				mT__40(); 

				}
				break;
			case 25 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:154: T__41
				{
				mT__41(); 

				}
				break;
			case 26 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:160: T__42
				{
				mT__42(); 

				}
				break;
			case 27 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:166: T__43
				{
				mT__43(); 

				}
				break;
			case 28 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:172: T__44
				{
				mT__44(); 

				}
				break;
			case 29 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:178: T__45
				{
				mT__45(); 

				}
				break;
			case 30 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:184: Register
				{
				mRegister(); 

				}
				break;
			case 31 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:193: Identifier
				{
				mIdentifier(); 

				}
				break;
			case 32 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:204: UnsignedDecimal
				{
				mUnsignedDecimal(); 

				}
				break;
			case 33 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:220: NegativeDecimal
				{
				mNegativeDecimal(); 

				}
				break;
			case 34 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:236: Hex
				{
				mHex(); 

				}
				break;
			case 35 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:240: Comment
				{
				mComment(); 

				}
				break;
			case 36 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:248: CommentZ
				{
				mCommentZ(); 

				}
				break;
			case 37 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:257: NewLine
				{
				mNewLine(); 

				}
				break;
			case 38 :
				// /Users/feeley/Documents/Work/Teaching/SimpleMachine/Grammar/Source/AsmSM213.g:1:265: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA10 dfa10 = new DFA10(this);
	static final String DFA10_eotS =
		"\10\uffff\6\24\1\50\5\24\1\uffff\1\27\2\uffff\1\62\6\uffff\4\24\1\67\4"+
		"\24\1\uffff\1\74\3\24\1\102\1\103\1\uffff\1\62\2\uffff\1\104\1\105\1\106"+
		"\1\107\1\uffff\1\111\1\112\1\24\1\115\1\uffff\1\116\1\117\1\120\1\121"+
		"\1\122\6\uffff\1\123\2\uffff\1\124\1\125\11\uffff";
	static final String DFA10_eofS =
		"\126\uffff";
	static final String DFA10_minS =
		"\1\11\5\uffff\1\141\1\uffff\1\144\2\145\1\160\1\141\1\156\1\60\1\144\2"+
		"\157\1\150\1\60\1\uffff\1\130\2\uffff\1\0\6\uffff\2\144\1\161\1\164\1"+
		"\60\2\143\1\154\1\143\1\uffff\1\60\1\166\1\160\1\154\2\60\1\uffff\1\0"+
		"\2\uffff\4\60\1\uffff\2\60\1\164\1\60\1\uffff\5\60\6\uffff\1\60\2\uffff"+
		"\2\60\11\uffff";
	static final String DFA10_maxS =
		"\1\172\5\uffff\1\160\1\uffff\1\156\1\162\1\145\1\160\1\141\1\156\1\172"+
		"\1\144\2\157\1\164\1\67\1\uffff\1\170\2\uffff\1\uffff\6\uffff\2\144\1"+
		"\161\1\164\1\172\2\143\1\154\1\143\1\uffff\1\172\1\166\1\164\1\162\2\172"+
		"\1\uffff\1\uffff\2\uffff\4\172\1\uffff\2\172\1\164\1\172\1\uffff\5\172"+
		"\6\uffff\1\172\2\uffff\2\172\11\uffff";
	static final String DFA10_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\uffff\1\12\14\uffff\1\37\1\uffff\1\41\1"+
		"\40\1\uffff\1\45\1\46\1\6\1\7\1\10\1\11\11\uffff\1\26\6\uffff\1\42\1\uffff"+
		"\1\43\1\44\4\uffff\1\17\4\uffff\1\27\5\uffff\1\35\1\36\1\13\1\14\1\15"+
		"\1\16\1\uffff\1\20\1\22\2\uffff\1\24\1\30\1\31\1\32\1\33\1\34\1\21\1\23"+
		"\1\25";
	static final String DFA10_specialS =
		"\30\uffff\1\0\27\uffff\1\1\45\uffff}>";
	static final String[] DFA10_transitionS = {
			"\1\32\1\31\2\uffff\1\31\22\uffff\1\32\2\uffff\1\30\1\1\3\uffff\1\2\1"+
			"\3\1\4\1\uffff\1\5\1\26\1\6\1\uffff\1\25\11\27\1\7\6\uffff\21\24\1\23"+
			"\10\24\4\uffff\1\24\1\uffff\1\10\1\11\1\24\1\12\2\24\1\13\1\14\1\15\1"+
			"\16\1\24\1\17\1\20\1\21\3\24\1\23\1\22\7\24",
			"",
			"",
			"",
			"",
			"",
			"\1\33\2\uffff\1\34\7\uffff\1\35\3\uffff\1\36",
			"",
			"\1\37\11\uffff\1\40",
			"\1\41\1\uffff\1\42\12\uffff\1\43",
			"\1\44",
			"\1\45",
			"\1\46",
			"\1\47",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\1\51",
			"\1\52",
			"\1\53",
			"\1\54\13\uffff\1\55",
			"\10\56",
			"",
			"\1\57\37\uffff\1\57",
			"",
			"",
			"\12\60\1\61\2\60\1\61\ufff2\60",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\1\70",
			"\1\71",
			"\1\72",
			"\1\73",
			"",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\1\75",
			"\1\76\3\uffff\1\77",
			"\1\100\5\uffff\1\101",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"",
			"\12\60\1\61\2\60\1\61\ufff2\60",
			"",
			"",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\1\110\31\24",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\1\113",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\1\114\31\24",
			"",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"",
			"",
			"",
			"",
			"",
			"",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"",
			"",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			""
	};

	static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
	static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
	static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
	static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
	static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
	static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
	static final short[][] DFA10_transition;

	static {
		int numStates = DFA10_transitionS.length;
		DFA10_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
		}
	}

	protected class DFA10 extends DFA {

		public DFA10(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 10;
			this.eot = DFA10_eot;
			this.eof = DFA10_eof;
			this.min = DFA10_min;
			this.max = DFA10_max;
			this.accept = DFA10_accept;
			this.special = DFA10_special;
			this.transition = DFA10_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | Register | Identifier | UnsignedDecimal | NegativeDecimal | Hex | Comment | CommentZ | NewLine | WS );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA10_24 = input.LA(1);
						s = -1;
						if ( ((LA10_24 >= '\u0000' && LA10_24 <= '\t')||(LA10_24 >= '\u000B' && LA10_24 <= '\f')||(LA10_24 >= '\u000E' && LA10_24 <= '\uFFFF')) ) {s = 48;}
						else if ( (LA10_24=='\n'||LA10_24=='\r') ) {s = 49;}
						else s = 50;
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA10_48 = input.LA(1);
						s = -1;
						if ( (LA10_48=='\n'||LA10_48=='\r') ) {s = 49;}
						else if ( ((LA10_48 >= '\u0000' && LA10_48 <= '\t')||(LA10_48 >= '\u000B' && LA10_48 <= '\f')||(LA10_48 >= '\u000E' && LA10_48 <= '\uFFFF')) ) {s = 48;}
						else s = 50;
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 10, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
