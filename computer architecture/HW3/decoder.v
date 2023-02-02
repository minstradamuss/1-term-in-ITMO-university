`include "instr.vh"



module decoder
(
    input   [31:0]  instr_i         ,
    output          i_type_o        ,
    output          j_type_o        ,
    output  [1:0]   src_rd_data_o   ,
    output  [3:0]   alu_operator_o  ,
    output          alu_operand_b_o ,
    output  [4:0]   rs1_addr_o      ,
    output  [4:0]   rs2_addr_o      ,
    output  [4:0]   rd_addr_o       ,
    output          rd_we_o         ,
    output          store_o         ,
    output          beq_o           ,
    output          bne_o           ,
    output          j_o             ,
    output          jal_o           ,
    output          jr_o
);

localparam [4:0] RA_REG             = 'd31;

wire [5:0] opcode;
assign opcode = instr_i[31:26];

wire [5:0] shamt;
assign shamt = instr_i[10:6];

wire [5:0] funct;
assign funct = instr_i[5:0];

wire [4:0] rs_reg;
assign rs_reg = instr_i[25:21];

wire [4:0] rt_reg;
assign rt_reg = instr_i[20:16];

wire [4:0] rd_reg;
assign rd_reg = instr_i[15:11];

wire opcode_zero;
assign opcode_zero = !(|opcode);

wire shamt_zero;
assign shamt_zero = !(|shamt);


wire is_lw;
assign is_lw = (opcode == 6'b100011) ? 1 : 0;

wire is_sw;
assign is_sw = (opcode == 6'b101011);

wire is_beq;
assign is_beq = (opcode == 6'b000100);

wire funct_add;
assign funct_add = (funct == 6'b100000);

wire funct_sub;
assign funct_sub = (funct == 6'b100010);

wire funct_and;
assign funct_and = (funct == 6'b100100);

wire funct_or;
assign funct_or = (funct == 6'b100101);

wire funct_slt;
assign funct_slt = (funct == 6'b101010);

wire funct_jr;
assign funct_jr = (funct == 6'b001000);


wire is_add;
assign is_add = opcode_zero && shamt_zero && funct_add;

wire is_sub;
assign is_sub = opcode_zero && shamt_zero && funct_sub;

wire is_and;
assign is_and = opcode_zero && shamt_zero && funct_and;

wire is_or;
assign is_or = opcode_zero && shamt_zero && funct_or;

wire is_slt;
assign is_slt = opcode_zero && shamt_zero && funct_slt;

wire is_addi;
assign is_addi = (opcode == 6'b001000);

wire is_andi;
assign is_andi = (opcode == 6'b001100);

wire is_bne;
assign is_bne = (opcode == 6'b000101);

wire is_j;
assign is_j = (opcode == 6'b000010);

wire is_jal;
assign is_jal = (opcode == 6'b000011);

wire is_jr;
assign is_jr = (opcode == 6'b000000) && !(|rt_reg) && !(|rd_reg) && !(|shamt) && funct_jr;

wire [3:0] alu_operator;
assign alu_operator = (is_lw || is_sw) ? `INSTR_ADD :
                      (is_addi || is_andi) ? {1'b0, opcode[2:0]} : funct[3:0];

wire i_type;
assign i_type =     is_lw
                ||  is_sw
                ||  is_beq
                ||  is_addi
                ||  is_andi
                ||  is_bne;

wire j_type;
assign j_type =     is_j
                ||  is_jal;

wire r_type;
assign r_type =     is_add
                ||  is_sub
                ||  is_and
                ||  is_or
                ||  is_slt;

wire rd_we;
assign rd_we =      is_lw
                ||  r_type
                ||  is_addi
                ||  is_andi
                ||  is_jal;

wire [4:0] rd_addr;
assign rd_addr = r_type ? rd_reg : i_type ? rt_reg : RA_REG;

reg [1:0] src_rd_data;


// Side
assign i_type_o = i_type;
assign j_type_o = j_type;
assign src_rd_data_o = src_rd_data;
assign alu_operator_o = alu_operator;
assign alu_operand_b_o = r_type;
assign rs1_addr_o = rs_reg;
assign rs2_addr_o = rt_reg;
assign rd_addr_o = rd_addr;
assign rd_we_o = rd_we;
assign store_o = is_sw;
assign beq_o = is_beq;
assign bne_o = is_bne;
assign j_o = is_j;
assign jal_o = is_jal;
assign jr_o = is_jr;


always @(*) begin
    src_rd_data = `SRC_RD_ALU_RESULT;

    if (is_lw) begin
        src_rd_data = `SRC_RD_MEM;
    end

    if (is_jal) begin
        src_rd_data = `SRC_RD_PC4;
    end
end

endmodule
