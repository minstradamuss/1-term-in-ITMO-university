`include "decoder.v"
`include "imm_prepare.v"
`include "branch.v"
`include "alu.v"



module mips_cpu
(

    input           clk                     , // сигнал синхронизации
    input   [31:0]  pc                      , // текущее значение регистра PC
    output  [31:0]  pc_new                  , // новое значение регистра PC (адрес следующей команды)

    output  [31:0]  instruction_memory_a    , // адреса памяти для записи или чтения памяти данных
    input   [31:0]  instruction_memory_rd   , // данные, полученные в результате чтения из памяти инструкций

    output  [31:0]  data_memory_a           , // адрес памяти данных
    input   [31:0]  data_memory_rd          , // данные чтения памяти данных
    output          data_memory_we          , // we для памяти данных
    output  [31:0]  data_memory_wd          , // данные для записи памяти данных

    output  [4:0]   register_a1             , // номера регистров
    output  [4:0]   register_a2             ,

    output  [4:0]   register_a3             ,
    output          register_we3            , // we3 для регистрового файла
    output  [31:0]  register_wd3            , // данные для записи в регистровый файл

    input   [31:0]  register_rd1            , // данные, полученные в результате чтения из регистрового файла
    input   [31:0]  register_rd2
);



reg [31:0] src_rd;

wire [31:0] pc4;
assign pc4 = pc + 3'd4;

wire [3:0] pc4_31_28;
assign pc4_31_28 = pc4[31:28];

wire [25:0] instr_25_0;
assign instr_25_0 = instruction_memory_rd[25:0];


// decoder
wire          i_type;
wire          j_type;
wire  [1:0]   src_rd_data;
wire  [3:0]   alu_operator;
wire          alu_operand_b;
wire          beq;
wire          bne;
wire          j;
wire          jal;
wire          jr;

// imm_prepare
wire [31:0] imm;

// branch
wire [31:0] branch_addr;
wire branch;

// alu
wire [31:0] alu_result;
wire [31:0] operand_b;
assign operand_b = (alu_operand_b == `ALU_OPERAND_REG) ? register_rd2 : imm;



// Side
assign pc_new               = branch ? branch_addr : pc4;
assign instruction_memory_a = pc;
assign data_memory_a        = alu_result;
assign register_wd3         = src_rd;
assign data_memory_wd       = register_rd2;



decoder decoder_impl
(
    .instr_i        (instruction_memory_rd),
    .i_type_o       (i_type),
    .j_type_o       (j_type),
    .src_rd_data_o  (src_rd_data),
    .alu_operator_o (alu_operator),
    .alu_operand_b_o(alu_operand_b),
    .rs1_addr_o     (register_a1),
    .rs2_addr_o     (register_a2),
    .rd_addr_o      (register_a3),
    .rd_we_o        (register_we3),
    .store_o        (data_memory_we),
    .beq_o          (beq),
    .bne_o          (bne),
    .j_o            (j),
    .jal_o          (jal),
    .jr_o           (jr)
);



imm_prepare imm_prepare_impl
(
    .instr_25_0_i(instr_25_0),
    .i_type_i    (i_type),
    .j_type_i    (j_type),
    .imm_o       (imm)
);



branch branch_impl
(
    .beq_i           (beq),
    .bne_i           (bne),
    .j_i             (j),
    .jal_i           (jal),
    .jr_i            (jr),
    .rs_i            (register_rd1),
    .rt_i            (register_rd2),
    .imm_i           (imm),
    .pc4_i           (pc4),
    .branch_addr_o   (branch_addr),
    .branch_o        (branch)
);



alu alu_impl
(
    .operator_i (alu_operator),
    .operand_a_i(register_rd1),
    .operand_b_i(operand_b),
    .result_o   (alu_result)
);



always @(*) begin
    case (src_rd_data)
        `SRC_RD_ALU_RESULT: src_rd = alu_result;
        `SRC_RD_MEM       : src_rd = data_memory_rd;
        `SRC_RD_PC4       : src_rd = pc4;
        default           : src_rd = alu_result;
    endcase
end



endmodule
