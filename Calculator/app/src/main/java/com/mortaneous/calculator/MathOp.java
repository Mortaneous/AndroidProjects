package com.mortaneous.calculator;

public class MathOp {
    private Double operand1;
    private Double operand2;
    private Double result;

    public MathOp() {
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Double getOperand1() {
        return operand1;
    }

    public void setOperand1(Double operand1) {
        this.operand1 = operand1;
    }

    public Double getOperand2() {
        return operand2;
    }

    public void setOperand2(Double operand2) {
        this.operand2 = operand2;
    }

    public void doAdd() { result = operand1 + operand2; }
    public void doSub() { result = operand1 - operand2; }
    public void doMul() { result = operand1 * operand2; }
    public void doDiv() throws Exception {
        if(operand2 == 0) {
            result = 0d;
            throw new Exception("Divide By Zero");
        }

        result = operand1 / operand2;
    }
}
