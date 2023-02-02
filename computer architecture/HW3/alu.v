`include "instr.vh"



module alu
(
    input   [3:0]   operator_i  ,
    input   [31:0]  operand_a_i ,
    input   [31:0]  operand_b_i ,
    output  [31:0]  result_o
);

wire result_slt;
reg [31:0]result;

assign result_slt = {31'b0, (operand_a_i[31] ^ operand_b_i[31])
                            ? operand_a_i[31] : operand_a_i < operand_b_i};

assign result_o = result;

always @(*) begin
    case (operator_i)
        `INSTR_ADD: result = operand_a_i + operand_b_i;
        `INSTR_SUB: result = operand_a_i - operand_b_i;
        `INSTR_AND: result = operand_a_i & operand_b_i;
        `INSTR_OR : result = operand_a_i | operand_b_i;
        `INSTR_SLT: result = result_slt;
        default: result = 'h0;
    endcase
end

endmodule
