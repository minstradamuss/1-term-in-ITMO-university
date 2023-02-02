


module not_gate (
    input in,
    output out
);

supply1 vdd;
supply0 gnd;

// p-канальный транзистор, сток = out, исток = vdd, затвор = in
pmos pmos1(out, vdd, in); // (сток, исток, база)
// n-канальный транзистор, сток = out, исток = gnd, затвор = in
nmos nmos1(out, gnd, in);

endmodule





module nand_gate (
    input in1,
    input in2,
    output out
);

supply0 gnd;
supply1 pwr;

wire nmos1_out;

// 2 p-канальных и 2 n-канальных транзистора
pmos pmos1(out, pwr, in1);
pmos pmos2(out, pwr, in2);
nmos nmos1(nmos1_out, gnd, in1);
nmos nmos2(out, nmos1_out, in2);

endmodule





module nor_gate (
    input in1,
    input in2,
    output out
);

supply0 gnd;
supply1 pwr;

wire pmos1_out;

pmos pmos1(pmos1_out, pwr, in1);
pmos pmos2(out, pmos1_out, in2);
nmos nmos1(out, gnd, in1);
nmos nmos2(out, gnd, in2);

endmodule





module and_gate (
    input in1,
    input in2,
    output out
);

wire nand_out;

// Схема для формулы AND(in1, in2) = NOT(NAND(in1, in2))
nand_gate nand_gate1(in1, in2, nand_out);
not_gate not_gate1(nand_out, out);

endmodule





module or_gate (
    input in1,
    input in2,
    output out
);

wire nor_out;

// Схема для формулы OR(in1, in2) = NOT(NOR(in1, in2))
nor_gate nor_gate1(in1, in2, nor_out);
not_gate not_gate1(nor_out, out);

endmodule





module xor_gate (
    input in1,
    input in2,
    output out
);

wire not_in1;
wire not_in2;

wire and_out1;
wire and_out2;

wire or_out1;

// Формула: XOR(in1, in2) = OR(AND(in1, NOT(in2)), AND(NOT(in1), in2))
not_gate not_gate1(in1, not_in1);
not_gate not_gate2(in2, not_in2);

and_gate and_gate1(in1, not_in2, and_out1);
and_gate and_gate2(not_in1, in2, and_out2);

or_gate or_gate1(and_out1, and_out2, out);

endmodule





module half_adder (
    input a,
    input b,
    output c_out,
    output s
);

/*
  Таблица истинности для полусумматора
  a b | c_out | s
  0 0 | 0     | 0
  0 1 | 0     | 1
  1 0 | 0     | 1
  1 1 | 1     | 0
*/

// c_out вычисляется как AND от a и b
and_gate and_gate1(a, b, c_out);
// s вычисляется как XOR от a и b
xor_gate xor_gate1(a, b, s);

endmodule





module full_adder (
    input c_in,
    input a,
    input b,
    output c_out,
    output s
);

/*
  Таблица истинности для полусумматора
   | c_in | a b | c_out | s
   | 0    | 0 0 | 0     | 0
   | 0    | 0 1 | 0     | 1
   | 0    | 1 0 | 0     | 1
   | 0    | 1 1 | 1     | 0
   | 1    | 0 0 | 0     | 1
   | 1    | 0 1 | 1     | 0
   | 1    | 1 0 | 1     | 0
   | 1    | 1 1 | 1     | 1
*/

wire half0_s;
wire half0_c_out;
wire half1_c_out;

half_adder half_adder_impl_0 (
    .a(a),
    .b(b),
    .c_out(half0_c_out),
    .s(half0_s)
);

half_adder half_adder_impl_1 (
    .a(half0_s),
    .b(c_in),
    .c_out(half1_c_out),
    .s(s)
);

or_gate or_gate_impl (
    .in1(half0_c_out),
    .in2(half1_c_out),
    .out(c_out)
);

endmodule




// Схема двоичного сумматора-вычитателя с выходами C и V
//  Если два двоичных числа считаются беззнаковыми, то бит C
// определяет перенос после сложения или заимствование после вычитания.
// Если числа считаются знаковыми, то бит V обнаруживает переполнение.
module adder_n #
(
    parameter WIDTH = 'd4
)
(
    input c_in,
    input [WIDTH-1:0] a,
    input [WIDTH-1:0] b,
    output c_out,
    output v_out,
    output [WIDTH-1:0] s
);

wire [WIDTH:0] c_out_ic;

assign c_out_ic[0] = c_in;
assign c_out = c_out_ic[WIDTH];

genvar i;

generate
    for (i = 0; i < WIDTH; i = i + 1) begin
        full_adder full_adder_impl
        (
            .c_in(c_out_ic[i]),
            .a(a[i]),
            .b(b[i]),
            .c_out(c_out_ic[i+1]),
            .s(s[i])
        );
    end
endgenerate

xor_gate xor_gate_impl
(
    .in1(c_out_ic[WIDTH]),
    .in2(c_out_ic[WIDTH-1]),
    .out(v_out)
);

endmodule





module and_n #
(
    parameter WIDTH = 'd4
)
(
    input [WIDTH-1:0] in1,
    input [WIDTH-1:0] in2,
    output [WIDTH-1:0] out
);

genvar i;

generate
    for (i = 0; i < WIDTH; i = i + 1) begin
        and_gate and_gate_impl
        (
            .in1(in1[i]),
            .in2(in2[i]),
            .out(out[i])
        );
    end
endgenerate

endmodule





module or_n #
(
    parameter WIDTH = 'd4
)
(
    input [WIDTH-1:0] in1,
    input [WIDTH-1:0] in2,
    output [WIDTH-1:0] out
);

genvar i;

generate
    for (i = 0; i < WIDTH; i = i + 1) begin
        or_gate or_gate_impl
        (
            .in1(in1[i]),
            .in2(in2[i]),
            .out(out[i])
        );
    end
endgenerate

endmodule





module not_n_en #
(
    parameter WIDTH = 'd4
)
(
    input en,
    input [WIDTH-1:0] in,
    output [WIDTH-1:0] out
);

genvar i;

generate
    for (i = 0; i < WIDTH; i = i + 1) begin
        xor_gate xor_gate_impl
        (
            .in1(en),
            .in2(in[i]),
            .out(out[i])
        );
    end
endgenerate

endmodule





module mux2_1 (
    input   s,
    input   d1,
    input   d2,
    output  y
);

wire and_d1_out;
wire and_d2_out;
wire sel_d0;

not_gate not_sel (
    .in(s),
    .out(sel_d0)
);

and_gate and_d1 (
    .in1(d1),
    .in2(sel_d0),
    .out(and_d1_out)
);

and_gate and_d2 (
    .in1(d2),
    .in2(s),
    .out(and_d2_out)
);

or_gate or_out (
    .in1(and_d1_out),
    .in2(and_d2_out),
    .out(y)
);

endmodule





module mux2_n #
(
    parameter WIDTH = 'd4
)
(
    input s,
    input [WIDTH-1:0] d1,
    input [WIDTH-1:0] d2,
    output [WIDTH-1:0] y
);

genvar i;

generate
    for (i = 0; i < WIDTH; i = i + 1) begin
        mux2_1 mux2_1_impl
        (
            .s(s),
            .d1(d1[i]),
            .d2(d2[i]),
            .y(y[i])
        );
    end
endgenerate

endmodule





module mux4_n #
(
    parameter WIDTH = 'd4
)
(
    input [1:0] s,
    input [WIDTH-1:0] d1,
    input [WIDTH-1:0] d2,
    input [WIDTH-1:0] d3,
    input [WIDTH-1:0] d4,
    output [WIDTH-1:0] y
);

wire [WIDTH-1:0] y_0;
wire [WIDTH-1:0] y_1;

mux2_n #
(
    .WIDTH(WIDTH)
)
mux2_n_impl_0
(
    .s(s[0]),
    .d1(d1),
    .d2(d2),
    .y(y_0)
);

mux2_n #
(
    .WIDTH(WIDTH)
)
mux2_n_impl_1
(
    .s(s[0]),
    .d1(d3),
    .d2(d4),
    .y(y_1)
);

mux2_n #
(
    .WIDTH(WIDTH)
)
mux2_n_impl_2
(
    .s(s[1]),
    .d1(y_0),
    .d2(y_1),
    .y(y)
);

endmodule





module mux8_n #
(
    parameter WIDTH = 'd4
)
(
    input [2:0] s,
    input [WIDTH-1:0] d1,
    input [WIDTH-1:0] d2,
    input [WIDTH-1:0] d3,
    input [WIDTH-1:0] d4,
    input [WIDTH-1:0] d5,
    input [WIDTH-1:0] d6,
    input [WIDTH-1:0] d7,
    input [WIDTH-1:0] d8,
    output [WIDTH-1:0] y
);

wire [WIDTH-1:0] y_0;
wire [WIDTH-1:0] y_1;

mux4_n #
(
    .WIDTH(WIDTH)
)
mux4_n_impl_0
(
    .s(s[1:0]),
    .d1(d1),
    .d2(d2),
    .d3(d3),
    .d4(d4),
    .y(y_0)
);

mux4_n #
(
    .WIDTH(WIDTH)
)
mux4_n_impl_1
(
    .s(s[1:0]),
    .d1(d5),
    .d2(d6),
    .d3(d7),
    .d4(d8),
    .y(y_1)
);

mux2_n #
(
    .WIDTH(WIDTH)
)
mux2_n_impl_2
(
    .s(s[2]),
    .d1(y_0),
    .d2(y_1),
    .y(y)
);

endmodule




module dmux1_2 (
    input sel,
    input in,
    output d1,
    output d2
);

wire sel_d1;

not_gate not_sel (
    .in(sel),
    .out(sel_d1)
);

and_gate and_d1 (
    .in1(sel_d1),
    .in2(in),
    .out(d1)
);

and_gate and_d2 (
    .in1(sel),
    .in2(in),
    .out(d2)
);

endmodule





module dmux_n_2 #
(
    parameter WIDTH = 'd4
)
(
    input sel,
    input [WIDTH-1:0] in,
    output [WIDTH-1:0] d1,
    output [WIDTH-1:0] d2
);

genvar i;

generate
    for (i = 0; i < WIDTH; i = i + 1) begin
        dmux1_2 dmux1_2_impl
        (
            .sel(sel),
            .in(in[i]),
            .d1(d1[i]),
            .d2(d2[i])
        );
    end
endgenerate

endmodule





module dmux_n_4 #
(
    parameter WIDTH = 'd4
)
(
    input [1:0] sel,
    input [WIDTH-1:0] in,
    output [WIDTH-1:0] d1,
    output [WIDTH-1:0] d2,
    output [WIDTH-1:0] d3,
    output [WIDTH-1:0] d4
);

wire [WIDTH-1:0] d1_0;
wire [WIDTH-1:0] d2_0;

dmux_n_2 #
(
    .WIDTH(WIDTH)
)
dmux_n_2_impl_0
(
    .sel(sel[1]),
    .in(in),
    .d1(d1_0),
    .d2(d2_0)
);

dmux_n_2 #
(
    .WIDTH(WIDTH)
)
dmux_n_2_impl_1
(
    .sel(sel[0]),
    .in(d1_0),
    .d1(d1),
    .d2(d2)
);

dmux_n_2 #
(
    .WIDTH(WIDTH)
)
dmux_n_2_impl_2
(
    .sel(sel[0]),
    .in(d2_0),
    .d1(d3),
    .d2(d4)
);

endmodule





module dmux_n_8 #
(
    parameter WIDTH = 'd4
)
(
    input [2:0] sel,
    input [WIDTH-1:0] in,
    output [WIDTH-1:0] d1,
    output [WIDTH-1:0] d2,
    output [WIDTH-1:0] d3,
    output [WIDTH-1:0] d4,
    output [WIDTH-1:0] d5,
    output [WIDTH-1:0] d6,
    output [WIDTH-1:0] d7,
    output [WIDTH-1:0] d8
);

wire [WIDTH-1:0] d1_0;
wire [WIDTH-1:0] d2_0;

dmux_n_2 #
(
    .WIDTH(WIDTH)
)
dmux_n_2_impl_0
(
    .sel(sel[2]),
    .in(in),
    .d1(d1_0),
    .d2(d2_0)
);

dmux_n_4 #
(
    .WIDTH(WIDTH)
)
dmux_n_4_impl_0
(
    .sel(sel[1:0]),
    .in(d1_0),
    .d1(d1),
    .d2(d2),
    .d3(d3),
    .d4(d4)
);

dmux_n_4 #
(
    .WIDTH(WIDTH)
)
dmux_n_4_impl_1
(
    .sel(sel[1:0]),
    .in(d2_0),
    .d1(d5),
    .d2(d6),
    .d3(d7),
    .d4(d8)
);

endmodule





module d_latch (
    // Сигнал синхронизации
    input clk,
    // Бит для записи в ячейку
    input d,
    // Необходимо ли перезаписать содержимое ячейки
    input we,
    // Сама ячейка
    output reg q
);

// Изначально в ячейке хранится 0
initial begin
    q <= 0;
end

// Значение изменяется на переданное на спаде сигнала синхронизации
always @ (negedge clk) begin
    if (we) begin
        q <= d;
    end
end

endmodule





module reg_n #
(
    parameter WIDTH = 'd4
)
(
    input clk,
    input [WIDTH-1:0] d,
    input we,
    output [WIDTH-1:0] q
);

genvar i;

generate
    for (i = 0; i < WIDTH; i = i + 1) begin
        d_latch d_latch_impl
        (
            .clk(clk),
            .d(d[i]),
            .we(we),
            .q(q[i])
        );
    end
endgenerate

endmodule





// App





module alu
(
    // Операнды
    input [3:0] a,
    input [3:0] b,
    // Управляющие сигналы для выбора операции
    input [2:0] control,
    // Результат
    output [3:0] res
);

localparam WIDTH = 'd4;

wire [WIDTH-1:0] b_out;
wire [WIDTH-1:0] and_n_out;
wire [WIDTH-1:0] or_n_out;
wire [WIDTH-1:0] adder_n_out;
wire adder_n_c_out;
wire adder_n_v_out;
wire xor_gate_out;

wire is_b_not;
assign is_b_not = control[2];

mux8_n #
(
    .WIDTH(WIDTH)
)
mux8_n_impl
(
    .s(control),
    .d1(and_n_out),     // a & b
    .d2(or_n_out),      // a | b
    .d3(adder_n_out),   // a + b
    .d4({WIDTH{1'd0}}), // not used
    .d5(and_n_out), // a & !b
    .d6(or_n_out), // a | !b
    .d7(adder_n_out), // a - b
    .d8({{WIDTH-1{1'd0}}, xor_gate_out}), // slt
    .y(res)
);

// ~b
not_n_en #
(
    .WIDTH(WIDTH)
)
not_n_en_impl
(
    .en(is_b_not),
    .in(b),
    .out(b_out)
);

// a & b
// a & !b (if set is_b_not)
and_n #
(
    .WIDTH(WIDTH)
)
and_n_impl
(
    .in1(a),
    .in2(b_out),
    .out(and_n_out)
);

// a | b
// a | !b (if set is_b_not)
or_n #
(
    .WIDTH(WIDTH)
)
or_n_impl
(
    .in1(a),
    .in2(b_out),
    .out(or_n_out)
);

// a + b
// a - b (if set is_b_not)
adder_n #
(
    .WIDTH(WIDTH)
)
adder_n_impl
(
    .c_in(is_b_not),
    .a(a),
    .b(b_out),
    .c_out(adder_n_c_out),
    .v_out(adder_n_v_out),
    .s(adder_n_out)
);

// Учитываем переполнение в случае когда скажем a = 3 и b = -5, результат сравнения
// должен быть a > b. Если не учитывать то старший бит в результате вычитания (a - b)
// который как правило используется для получения флага сравнения a < b, будет указывать на
// результат разницы 3 - (-5) = 8, а не знак отрицательного числа.
xor_gate xor_gate_impl
(
    .in1(adder_n_v_out),
    .in2(adder_n_out[WIDTH-1]),
    .out(xor_gate_out)
);

endmodule





module register_file
(
    // Сигнал синхронизации
    input clk,
    // Номера регистров для чтения и записи
    input [1:0] rd_addr,
    input [1:0] we_addr,
    // Данные для записи в регистровый файл
    input [3:0] we_data,
    // Данные, полученные в результате чтения из регистрового файла
    output [3:0] rd_data
);

localparam REGS  = 'd4;
localparam WIDTH = 'd4;

wire [WIDTH-1:0] we;
wire [WIDTH-1:0] reg_out [REGS-1:0];

genvar i;

generate
    for (i = 0; i < WIDTH; i = i + 1) begin
        reg_n # (
            .WIDTH(WIDTH)
        ) reg_n_impl (
            .clk(clk),
            .d(we_data),
            .we(we[i]),
            .q(reg_out[i])
        );
    end
endgenerate

// Read
mux4_n # (

    .WIDTH(WIDTH)
)
mux4_n_impl (
    .s(rd_addr),
    .d1(reg_out[0]),
    .d2(reg_out[1]),
    .d3(reg_out[2]),
    .d4(reg_out[3]),
    .y(rd_data)
);

// Write enable
dmux_n_4 #
(
    .WIDTH(1)
)
dmux_n_4_impl
(
    .sel(we_addr),
    .in(1'd1),
    .d1(we[0]),
    .d2(we[1]),
    .d3(we[2]),
    .d4(we[3])
);

endmodule





module calculator
(
    // Сигнал синхронизации
    input clk,
    // Номер регистра, из которого берется значение первого операнда
    input [1:0] rd_addr,
    // Целочисленная константа, выступающая вторым операндом
    input [3:0] immediate,
    // Номер регистра, куда производится запись результата операции
    input [1:0] we_addr,
    // Управляющие сигналы для выбора операции
    input [2:0] control,
    // Данные из регистра c номером 'rd_addr', подающиеся на выход
    output [3:0] rd_data
);

wire [3:0] res_out;

alu alu_impl (
    .a(rd_data),
    .b(immediate),
    .control(control),
    .res(res_out)
);

register_file register_file_impl (
    .clk(clk),
    .rd_addr(rd_addr),
    .we_addr(we_addr),
    .we_data(res_out),
    .rd_data(rd_data)
);

endmodule