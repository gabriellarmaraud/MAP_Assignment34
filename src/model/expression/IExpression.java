
package model.expression;

import exceptions.MyException;
import model.myDictionary.MyIDictionary;
import model.myHeap.MyIHeap;
import model.value.Value;

public interface IExpression {
    Value eval(MyIDictionary<String, Value> table, MyIHeap heap) throws MyException;
    IExpression deepCopy();
}
