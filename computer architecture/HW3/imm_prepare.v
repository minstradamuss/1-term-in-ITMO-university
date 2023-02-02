


module imm_prepare
(
    input   [25:0]  instr_25_0_i,
    input           i_type_i    ,
    input           j_type_i    ,
    output  [31:0]  imm_o
);

reg [31:0] imm;

assign imm_o = imm;

always @(*) begin
    imm = 'd0;

    if (i_type_i) begin
        imm = {{'d16{instr_25_0_i[15]}}, instr_25_0_i[15:0]};
    end

    if (j_type_i) begin
        imm = {4'b0000, instr_25_0_i, 2'b00};
    end
end

endmodule
