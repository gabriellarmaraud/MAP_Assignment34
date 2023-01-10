package view;

import controller.Controller;
import exceptions.MyException;
import model.expression.ArithmeticExpression;
import model.expression.RelationalExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.expression.ReadHeapExpression;
import model.myDictionary.MyDictionary;
import model.myDictionary.MyIDictionary;
import model.myList.MyIList;
import model.myList.MyList;
import model.myStack.MyIStack;
import model.myStack.MyStack;
import model.programState.ProgramState;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.StringType;
import model.type.RefType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import repository.IRepository;
import repository.Repository;
import model.myHeap.MyIHeap;
import model.myHeap.MyHeap;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class View {
    public void start() {
        boolean done = false;
        while (!done) {
            try {
                showMenu();
                Scanner readOption = new Scanner(System.in);
                int option = readOption.nextInt();
                if (option == 0) {
                    done = true;
                } else if (option == 1) {
                    runProgram1();
                } else if (option == 2) {
                    runProgram2();
                } else if (option == 3) {
                    runProgram3();
                } else if (option == 4) {
                    runProgram4();
                }else if (option == 5) {
                    runProgram5();
                }else if (option == 6) {
                    runProgram6();
                }
                else if (option == 7) {
                    runProgram7();
                }else if (option == 8) {
                    runProgram8();
                }else if (option == 9) {
                    runProgram9();
                }
                else {
                    System.out.println("Invalid input!");
                }
            } catch (Exception exception) {
                System.out.println("\u001B[31m" + exception.getMessage() + "\u001B[0m");
            }
        }
    }

    private void showMenu() {
        System.out.println("MENU: ");
        System.out.println("0. Exit.");
        System.out.println("1. Run the first program: \n\tint v;\n\tv=2;\n\tPrint(v)");
        System.out.println("2. Run the second program: \n\tint a;\n\tint b;\n\ta=2+3*5;\n\tb=a+1;\n\tPrint(b)");
        System.out.println("3. Run the third program: \n\tbool a;\n\tint v;\n\ta=true;\n\t(If a Then v=2 Else v=3);\n\tPrint(v)");
        System.out.println("4. Run the fourth program : \n\tstring varf;\n\tvarf='test.in';\n\tint varc\n\treadFile(varf,varc);print(varc);\n\treadFile(varf,varc);print(varc)\n\tcloseRFile(varf)");
        System.out.println("5. Run the fifth program: \n\tint a;\n\tint b;\n\ta=5\n\tb=7\n\tif(a>b){\n\tPrint(a)}else{Print(b)}");
        System.out.println("6. Run the sixth program: \n\tint v;\n\tv=4;\n\twhile(v>0){Print(v)\n\tv=v-1}\n\tPrint(v)");
        System.out.println("7. Run the seventh program : \n\tstring varf;\n\tvarf='test.in';\n\tint varc\n\treadFile(varf,varc);print(varc);\n\treadFile(varf,varc);print(varc)\n\tcloseRFile(varf)");
        System.out.println("8. Run the eighth program: \n\tint a;\n\tint b;\n\ta=5\n\tb=7\n\tif(a>b){\n\tPrint(a)}else{Print(b)}");
        System.out.println("9. Run the ninth program: \n\tint v;\n\tv=4;\n\twhile(v>0){Print(v)\n\tv=v-1}\n\tPrint(v)");
        System.out.println("Choose an option: ");
    }

    private void runProgram1() throws MyException, IOException {
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        runStatement(ex1);
    }

    private void runProgram2() throws MyException, IOException {
        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        runStatement(ex2);
    }

    private void runProgram3() throws MyException, IOException {
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        runStatement(ex3);
    }
    private void runProgram4() throws MyException, IOException{
        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFile(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseReadFile(new VariableExpression("varf"))))))))));
        runStatement((ex4));
    }

    private void runProgram5() throws MyException, IOException {
        IStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new IntValue(5))),
                                new CompoundStatement(new AssignStatement("b", new ValueExpression(new IntValue(7))),
                                        new IfStatement(new RelationalExpression(">", new VariableExpression("a"),
                                                new VariableExpression("b")),new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b")))))));
        runStatement(ex5);
    }
    private void runProgram6() throws MyException, IOException {
        IStatement ex6 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(">", new VariableExpression("v"), new ValueExpression(new IntValue(0))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v",new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));
        runStatement(ex6);
    }
    private void runProgram7() throws MyException, IOException {
        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));
        ProgramState prg7 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex7);
        IRepository repo7 = new Repository(prg7, "log7.txt");
        Controller controller7 = new Controller(repo7);
        runStatement(ex7);
    }
    private void runProgram8() throws MyException, IOException {
        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression('+', new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))))))));
        ProgramState prg8 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex8);
        IRepository repo8 = new Repository(prg8, "log8.txt");
        Controller controller8 = new Controller(repo8);
        runStatement(ex8);
    }
    private void runProgram9() throws MyException, IOException {

            IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                    new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                            new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                    new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                            new PrintStatement(new ArithmeticExpression('+', new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))))));
            ProgramState prg9 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex9);
            IRepository repo9 = new Repository(prg9, "log9.txt");
            Controller controller9 = new Controller(repo9);
        runStatement(ex9);
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
        System.out.println("Result is" + state.getOut().toString().replace('[', ' ').replace(']', ' '));
    }

}
