# Modified Wiegand Encoder & Decoder (Java)

This Java project implements a **Modified Wiegand protocol** encoder and decoder using two signal lines: `D0` and `D1`. Unlike the standard Wiegand format, this version does **not use a separate clock signal** — instead, bit transitions and toggles are used for synchronization.

---

## 📘 Problem Overview

In the **Modified Wiegand** protocol:
- Two lines are used: `D0` and `D1`
- To transmit:
  - Bit `0` → `D0 = 0`, `D1 = 1`
  - Bit `1` → `D0 = 1`, `D1 = 0`
- For **consecutive same bits**, toggles are used instead of repeating the same signal:
  - If consecutive bits are `1`, `D0` toggles
  - If consecutive bits are `0`, `D1` toggles
- When both `D0` and `D1` are `1`, the stream ends

### 🧮 Decoding Rules

To convert back from `D0` and `D1` to the original serial stream:

| D0 | D1 | Result        |
|----|----|---------------|
| 0  | 1  | Bit = 0       |
| 1  | 0  | Bit = 1       |
| 0  | toggles | Repeat 0 |
| toggles | 0 | Repeat 1  |
| 1  | 1  | End of stream |

---

## 📌 Example

Given bitstream: `1011010001`

The encoded lines:
Input: 1 0 1 1 0 1 0 0 0 1
D0: 1 0 1 T 0 1 0 0 0 1
D1: 0 1 0 0 1 0 1 T T 0

Where:
- `T` = toggled bit (repeated value using line toggling)
- The toggling acts as the clock signal

Another test case:

D0 = 1010010001
D1 = 0100101010

Decoded bits: `1011010001`

---

## 🛠️ Project Structure

ModifiedWiegand/
│
├── BitUtils.java       # Bit-level read/write operations  
├── ModifiedWiegand.java # Core encoding & decoding logic  
├── WiegandIO.java      # User input/output and validation  
└── Main.java           # Program entry point  

---

## 💻 How to Run

### 🔹 Compile:
```bash
javac *.java
🔹 Run:
