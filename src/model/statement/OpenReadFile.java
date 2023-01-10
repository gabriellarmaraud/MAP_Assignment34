package model.statement;

import exceptions.MyException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.type.StringType;
import model.myDictionary.MyIDictionary;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFile implements IStatement{
    private final IExpression expression;

    public OpenReadFile(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        Value value = expression.eval(state.getSymTable(), state.getHeap());
        StringValue fileName = (StringValue) value;
        if (value.getType().equals(new StringType())) {
            throw new MyException(String.format("%s is already opened", fileName.getValue()));
        }
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName.getValue())) {
            throw new MyException(String.format("%s does not evaluate to StringType", expression.toString()));
        }
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(fileName.getValue()));
            } catch (FileNotFoundException e) {
                throw new MyException(String.format("%s could not be opened", fileName.getValue()));
            }
            fileTable.put(fileName.getValue(), br);
            state.setFileTable(fileTable);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenReadFile(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("OpenReadFile(%s)", expression.toString());
    }
}