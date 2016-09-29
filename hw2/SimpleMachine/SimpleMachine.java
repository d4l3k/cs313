import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import ui.AbstractUI;

public class SimpleMachine {
  
  /**
   * Generic entry-point for executing the simple machine.
   * @param args command-line arguments, using the following syntax:
   *             "-i [cli|gui] -a [sm213|y86seq|y86pipeminus|y86pipe|y86pipesuper] -v [solution|student]".
   *             additional arguments are defined by specific user-interface implementation.
   */
  
  public final static class Sm213 {
    public static void main (String[] args) {
      SimpleMachine.main (args, "sm213", "solution");
    }
  }
  
  public final static class Sm213Vm {
    public static void main (String[] args) {
      SimpleMachine.main (args, "sm213-vm", "solution");
    }
  }
  
  public final static class Y86Seq {
    public static void main (String[] args) {
      SimpleMachine.main (args, "y86-seq", "solution");
    }
  }
  
  public final static class Y86PipeMinus {
    public static void main (String[] args) {
      SimpleMachine.main (args, "y86-pipe-minus", "solution");
    }
  }
  
  public final static class Y86Pipe {
    public static void main (String[] args) {
      SimpleMachine.main (args, "y86-pipe", "solution");
    }
  }
  
  public final static class Y86PipeSuper {
    public static void main (String[] args) {
      SimpleMachine.main (args, "y86-pipesuper", "solution");
    }
  }
  
  public final static class Sm213Student {
    public static void main (String[] args) {
      SimpleMachine.main (args, "sm213", "student");
    }
  }
  
  public final static class Sm213VmStudent {
    public static void main (String[] args) {
      SimpleMachine.main (args, "sm213-vm", "student");
    }
  }
  
  public final static class Y86SeqStudent {
    public static void main (String[] args) {
      SimpleMachine.main (args, "y86-seq", "student");
    }
  }
  
  public final static class Y86PipeMinusStudent {
    public static void main (String[] args) {
      SimpleMachine.main (args, "y86-pipe-minus", "student");
    }
  }
  
  public final static class Y86PipeStudent {
    public static void main (String[] args) {
      SimpleMachine.main (args, "y86-pipe", "student");
    }
  }
  
  public final static void main (String[] args) {
    AbstractUI.main (args);
  }
  
  private final static void main (String[] args, String arch, String var) {
    List <String> argsList = new ArrayList<String> (Arrays.asList (args));
    if (! argsList.contains ("-a"))
      argsList.addAll (Arrays.asList (new String[] {"-a", arch}));
    if (! argsList.contains ("-v"))
      argsList.addAll (Arrays.asList (new String[] {"-v", var}));
    args = argsList.toArray (new String[0]);
    SimpleMachine.main (args);
  }
}