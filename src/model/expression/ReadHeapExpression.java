package model.expression;

import exceptions.MyException;
import model.myDictionary.MyIDictionary;
import model.myHeap.MyIHeap;
import model.value.RefValue;
import model.value.Value;

public class ReadHeapExpression implements IExpression{
    private final IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws MyException {
        Value value = expression.eval(symTable, heap);
        if (!(value instanceof RefValue))
            throw new MyException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        return heap.get(refValue.getAddress());
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("ReadHeap(%s)", expression);
    }
}