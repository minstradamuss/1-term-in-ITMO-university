

`define INSTR_ADD 4'b0000
`define INSTR_SUB 4'b0010
`define INSTR_AND 4'b0100
`define INSTR_OR  4'b0101
`define INSTR_SLT 4'b1010

`define SRC_RD_ALU_RESULT   2'd0
`define SRC_RD_MEM          2'd1
`define SRC_RD_PC4          2'd2

`define ALU_OPERAND_IMM     'd0
`define ALU_OPERAND_REG     'd1
