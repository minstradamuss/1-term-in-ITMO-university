`include "mips_cpu.v"
`include "memory.v"
`include "reg_file.v"



module cpu_test #
(
    parameter DATA_DEPTH        = 2048,
    parameter INSTR_DEPTH       = 2048,
    parameter INSTR_INIT_FILE   = "instructions.dat"
)
(
    input clk
);

reg [31:0] pc = 0;

// mips_cpu
wire [31:0] pc_new;
wire [31:0] instruction_memory_a;
wire [31:0] data_memory_a;
wire        data_memory_we;
wire [31:0] data_memory_wd;
wire [4:0]  register_a1;
wire [4:0]  register_a2;
wire [4:0]  register_a3;
wire        register_we3;
wire [31:0] register_wd3;

// reg_file
wire [31:0] rs1_data;
wire [31:0] rs2_data;

// instr_mem
wire [31:0] instr_mem_rd;

// data_mem
wire [31:0] data_mem_rd;



mips_cpu mips_cpu_impl
(

    .clk                  (clk),
    .pc                   (pc),
    .pc_new               (pc_new),
    .instruction_memory_a (instruction_memory_a),
    .instruction_memory_rd(instr_mem_rd),
    .data_memory_a        (data_memory_a),
    .data_memory_rd       (data_mem_rd),
    .data_memory_we       (data_memory_we),
    .data_memory_wd       (data_memory_wd),
    .register_a1          (register_a1),
    .register_a2          (register_a2),
    .register_a3          (register_a3),
    .register_we3         (register_we3),
    .register_wd3         (register_wd3),
    .register_rd1         (rs1_data),
    .register_rd2         (rs2_data)
);



reg_file reg_file_impl
(
    .clk_i      (clk),
    .rs1_addr_i (register_a1),
    .rs1_data_o (rs1_data),
    .rs2_addr_i (register_a2),
    .rs2_data_o (rs2_data),
    .rd_addr_i  (register_a3),
    .rd_data_i  (register_wd3),
    .rd_we_i    (register_we3)
);



memory #
(
    .DEPTH(INSTR_DEPTH),
    .EN_INIT_FILE(1),
    .INIT_FILE(INSTR_INIT_FILE)
)
instr_mem
(
    .clk_i   (clk),
    .a_i     (instruction_memory_a),
    .we_i    (1'b0),
    .wd_i    (32'd0),
    .rd_o    (instr_mem_rd)
);



memory #
(
    .DEPTH(DATA_DEPTH),
    .EN_INIT_FILE(0)
)
data_mem
(
    .clk_i   (clk),
    .a_i     (data_memory_a),
    .we_i    (data_memory_we),
    .wd_i    (data_memory_wd),
    .rd_o    (data_mem_rd)
);



always @(posedge clk) begin
    pc <= pc_new;
end



endmodule
