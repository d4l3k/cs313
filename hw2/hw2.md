# CS313 - HW2
Tristan Rice, q7w9a, 25886145

## Part 1
### 1. Find the bugs

First bug was that only srcA was being checked for data hazards. No stalling was
occuring for things that used srcB such as `addl`. Added a check for
isDataHazardOnReg for srcB in pipelineHazardControl.

Second bug was that dstM wasn't being checked as a potential data hazard, thus
wasn't stalling when it was set and then read from. Added
cases for dstM to the logic statement in isDataHazardOnReg.

Third bug is related to conditional jumps. Things aren't correctly being
stalled, when a conditional jump executes. The PC is predicted to follow the
jump, and executes the first instruction before reverting back.

## Part 2

Changes work with fwdOrder, srcAHzd, sBHazard, aLoadUse, bLoadUse. Doesn't seem
to work with cmov. Maybe because it's not forwarding the condition codes.
