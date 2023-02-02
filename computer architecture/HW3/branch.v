


module branch
(
    input           beq_i           ,
    input           bne_i           ,
    input           j_i             ,
    input           jal_i           ,
    input           jr_i            ,
    input   [31:0]  rs_i            ,
    input   [31:0]  rt_i            ,
    input   [31:0]  imm_i           ,
    input   [31:0]  pc4_i           ,
    output  [31:0]  branch_addr_o   ,
    output          branch_o
);

wire beq_result;
wire bne_result;
reg branch;
reg [31:0] branch_addr;

assign beq_result = rs_i == rt_i;

assign bne_result = !beq_result;

assign branch_o = branch;

assign branch_addr_o = branch_addr;

always @(*) begin
    branch = 'd0;
    // branch_addr = (j_i || jal_i) ? imm_i : pc4_i + {imm_i[29:0], 2'b00};
    // branch_addr = jr_i ? rs_i : branch_addr;

    branch_addr = pc4_i + {imm_i[29:0], 2'b00};

    if (j_i || jal_i) begin
        branch_addr = imm_i;
    end

    if (jr_i) begin
        branch_addr = rs_i;
    end

    if (beq_result) begin
        branch = beq_i;
    end

    if (bne_result) begin
        branch = bne_i;
    end

    if (j_i) begin
        branch = 'd1;
    end

    if (jal_i) begin
        branch = 'd1;
    end

    if (jr_i) begin
        branch = 'd1;
    end
end

endmodule
