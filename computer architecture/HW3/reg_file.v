


module reg_file
(
    input           clk_i       ,

    // Read port RS1
    input   [4:0]   rs1_addr_i  ,
    output  [31:0]  rs1_data_o  ,

    // Read port RS2
    input   [4:0]   rs2_addr_i  ,
    output  [31:0]  rs2_data_o  ,

    // Write port RD1
    input   [4:0]   rd_addr_i   ,
    input   [31:0]  rd_data_i   ,
    input           rd_we_i
);

wire rd_we;
reg [31:0] mem[0:31];

integer i;

initial begin
    for (i = 0; i < 32; i = i + 1) begin
        mem[i] = 0;
    end
end

// R0 = $zero = The Constant Value 0
assign rd_we = (rd_addr_i == 'h0) ? 'h0 : rd_we_i;

assign rs1_data_o = mem[rs1_addr_i];
assign rs2_data_o = mem[rs2_addr_i];

always @(posedge clk_i) begin
    if (rd_we) begin
        mem[rd_addr_i] <= rd_data_i;
    end
end

endmodule
