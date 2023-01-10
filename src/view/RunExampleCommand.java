package view;

import controller.Controller;
import exceptions.MyException;
import model.myDictionary.MyDictionary;
import model.myDictionary.MyIDictionary;
import model.myHeap.MyHeap;
import model.myHeap.MyIHeap;
import model.myList.MyIList;
import model.myList.MyList;
import model.myStack.MyIStack;
import model.myStack.MyStack;
import model.programState.ProgramState;
import model.statement.IStatement;
import model.value.Value;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class RunExampleCommand extends Command{
    private final Controller controller;
    IStatement statement;

    public RunExampleCommand(String key, String description, Controller controller,IStatement statement) {
        super(key, description);
        this.statement=statement;
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            runStatement(statement);
        } catch (MyException | IOException exception) {
            System.out.println("\u001B[31m" + exception.getMessage() + "\u001B[0m");
        }
    }
    private void runStatement(IStatement statement) throws MyException, IOException {
        MyIStack<IStatement> executionStack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap heap = new MyHeap();

        ProgramState state = new ProgramState(executionStack, symbolTable, output, fileTable, heap, statement);

        IRepository repository = new Repository(state, "log.txt");
        repository.emptyLogFile();
        Controller controller = new Controller(repository);

        System.out.println("Do you want to display the steps?[y/n]");
        Scanner readOption = new Scanner(System.in);
        String option = readOption.next();
        controller.setDisplayFlag(Objects.equals(option, "y"));
        controller.allSteps();

        controller.allSteps();
        System.out.println("Result is" + state.getOut().toString().replace('[', ' ').replace(']', ' '));
    }
}
