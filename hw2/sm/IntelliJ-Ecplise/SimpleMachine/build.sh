#!/usr/bin/env zsh
echo "Building..."
javac -verbose -cp SimpleMachineStudent313.jar:SimpleMachineStudentDoc313.zip:SimpleMachineStudentSrc313.zip:. -d bin src/**/*.java
echo "Done."
