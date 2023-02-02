// Реализация логического вентиля NOT с помощью структурных примитивов
module not_gate(in, out);
  // Входные порты помечаются как input, выходные как output
  input wire in;
  output wire out;
  // Ключевое слово wire для обозначения типа данных можно опустить,
  // тогда оно подставится неявно, например:
  /*
    input in;
    output out;
  */

  supply1 vdd; // Напряжение питания
  supply0 gnd; // Напряжение земли

  // p-канальный транзистор, сток = out, исток = vdd, затвор = in
  pmos pmos1(out, vdd, in); // (сток, исток, база)
  // n-канальный транзистор, сток = out, исток = gnd, затвор = in
  nmos nmos1(out, gnd, in);
endmodule

// Реализация NAND с помощью структурных примитивов
module nand_gate(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;

  supply0 gnd;
  supply1 pwr;

  // С помощью типа wire можно определять промежуточные провода для соединения элементов.
  // В данном случае nmos1_out соединяет сток транзистора nmos1 и исток транзистора nmos2.
  wire nmos1_out;

  // 2 p-канальных и 2 n-канальных транзистора
  pmos pmos1(out, pwr, in1);
  pmos pmos2(out, pwr, in2);
  nmos nmos1(nmos1_out, gnd, in1);
  nmos nmos2(out, nmos1_out, in2);
endmodule

// Реализация NOR с помощью структурных примитивов
module nor_gate(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;

  supply0 gnd;
  supply1 pwr;

  // Промежуточный провод, чтобы содединить сток pmos1 и исток pmos2
  wire pmos1_out;

  pmos pmos1(pmos1_out, pwr, in1);
  pmos pmos2(out, pmos1_out, in2);
  nmos nmos1(out, gnd, in1);
  nmos nmos2(out, gnd, in2);
endmodule

// Реализация AND с помощью NAND и NOT
module and_gate(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;

  // Промежуточный провод, чтобы передать выход вентиля NAND на вход вентилю NOT
  wire nand_out;

  // Схема для формулы AND(in1, in2) = NOT(NAND(in1, in2))
  nand_gate nand_gate1(in1, in2, nand_out);
  not_gate not_gate1(nand_out, out);
endmodule

// Реализация OR с помощью NOR и NOT
module or_gate(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;

  wire nor_out;

  // Схема для формулы OR(in1, in2) = NOT(NOR(in1, in2))
  nor_gate nor_gate1(in1, in2, nor_out);
  not_gate not_gate1(nor_out, out);
endmodule

module ternary_min(a[1:0], b[1:0], out[1:0]);
  input [1:0] a;
  input [1:0] b;
  output [1:0] out;
  // TODO: implementation
  //  "-"      00
  //  "0"      01
  //  "+"      10

  wire c, d, e;

  and_gate and_gate1(b[1], a[1], out[1]);

  or_gate or_gate1(b[1], b[0], c);
  and_gate and_gate2(c, a[0], d);
  and_gate and_gate3(a[1], b[0], e);
  or_gate or_gate2( d, e, out[0]);

endmodule

module ternary_max(a[1:0], b[1:0], out[1:0]);
  input [1:0] a;
  input [1:0] b;
  output [1:0] out;
  // TODO: implementation

  wire c, d;

  or_gate or_gate1(a[1], b[1], out[1]);

  or_gate or_gate2(a[0], b[0], c);
  not_gate not_gate1(out[1], d);
  and_gate and_gate2(c, d, out[0]);
  
endmodule

module ternary_any(a[1:0], b[1:0], out[1:0]);
  input [1:0] a;
  input [1:0] b;
  output [1:0] out;
  // TODO: implementation
  wire an[1:0], bn[1:0], d[2:0], e[2:0], e1[1:0],
       f, g;

  not_gate not_gate1(a[0], an[0]);
  not_gate not_gate2(a[1], an[1]);
  not_gate not_gate3(b[0], bn[0]);
  not_gate not_gate4(b[1], bn[1]);

  and_gate and_gateE1(an[0], an[1],  e1[0]);
  and_gate and_gateE2(bn[0], bn[1],  e1[1]);

  and_gate and_gate1(a[0], b[1], d[0]);
  and_gate and_gate2(a[1], b[0], d[1]);
  and_gate and_gate3(a[1], b[1], d[2]);

  or_gate or_gate1(d[0], d[1], f);
  or_gate or_gate2(f, d[2], out[1]);

  and_gate and_gate4(a[0],   b[0],   e[0]);
  and_gate and_gate5(a[1],   e1[1],  e[1]);
  and_gate and_gate6(e1[0],  b[1],   e[2]);

  or_gate or_gate3(e[0], e[1], g);
  or_gate or_gate4(g, e[2], out[0]);
endmodule

module ternary_consensus(a[1:0], b[1:0], out[1:0]);
  input [1:0] a;
  input [1:0] b;
  output [1:0] out;
  // TODO: implementation

  wire d, e, f, no1;

  and_gate and_gate1(a[1], b[1], out[1]);
  not_gate not_gate1(out[1], no1 );

  or_gate or_gate1(a[1], a[0],  e);
  or_gate or_gate2(b[1], b[0], f);
  or_gate or_gate3(e, f, d);
  and_gate and_gate2(no1, d, out[0]);
endmodule


