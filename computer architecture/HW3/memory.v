


module memory #
(
    parameter DEPTH         = 2048,
    parameter EN_INIT_FILE  = 0,
    parameter INIT_FILE     = "instructions.dat"
)
(
    input   [31:0]  a_i     ,
    input           we_i    ,
    input           clk_i   ,
    input   [31:0]  wd_i    ,
    output  [31:0]  rd_o
);

reg [31:0] ram[0:DEPTH-1];

integer i;

initial begin
    if (EN_INIT_FILE) begin
        $readmemb(INIT_FILE, ram);
    end else begin
        for (i = 0; i < 2048; i = i + 1) begin
            ram[i] = 0;
        end
    end
end

// чтение происходит из элемента массива по a / 4, т.к.
// адресация побайтовая, а ячейки массива по 32 бита (4 байта)
assign rd_o = ram[a_i / 4];

always @ (posedge clk_i) begin
    if (we_i) begin
        ram[a_i / 4] <= wd_i;
    end
end

endmodule
