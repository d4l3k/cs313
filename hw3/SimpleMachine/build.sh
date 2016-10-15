#!/usr/bin/env bash
echo "Building..."
javac -verbose -cp SimpleMachineStudent313.jar:SimpleMachineStudentDoc313.zip:SimpleMachineStudentSrc313.zip:. -d bin src/arch/y86/machine/pipe/student/CPU.java
echo "Done."
