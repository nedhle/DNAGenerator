package main;

public class DNAGeneratorMain {
    public static void main(String[] args) {
        GeneratorEngine g = new GeneratorEngine();
        g.generate();
        g.printResponseMatrix2Console();
    }
}
