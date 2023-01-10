package model.statement;

import exceptions.MyException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.type.StringType;
import model.myDictionary.MyIDictionary;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFile implements IStatement{
    private final IExpression expression;

    public CloseReadFile(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        Value value = expression.eval(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType()))
            throw new MyException(String.format("%s does not evaluate to StringValue", expression));
        StringValue fileName = (StringValue) value;
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName.getValue()))
            throw new MyException(String.format("%s is not present in the FileTable", value));
        BufferedReader br = fileTable.lookUp(fileName.getValue());
        try {
            br.close();
            fileTable.remove(fileName.getValue());
            state.setFileTable(fileTable);
        } catch (IOException e) {
            throw new MyException(String.format("Unexpected error in closing %s", value));
        }

        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseReadFile(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("CloseReadFile(%s)", expression.toString());
    }
}