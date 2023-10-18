import java.io.*;

public class Main {
    public static void main(String[] args) {
        //Dosya Olusturma
        File file = new File("sayilar.txt");
        if(file.exists()) {
            System.out.println("Dosya mevcut");
        } else {
            System.out.println("Dosya mevcut degil");
        }


        try {
           FileWriter fileWriter = new FileWriter(file, false);
           BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("5");
            bufferedWriter.newLine();
            bufferedWriter.write("10");
            bufferedWriter.newLine();
            bufferedWriter.write("20");
            bufferedWriter.newLine();
            bufferedWriter.write("12");
            bufferedWriter.newLine();
            bufferedWriter.write("33");
            bufferedWriter.newLine();
            bufferedWriter.close();

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int sum = 0;

            while((line = bufferedReader.readLine()) != null) {
                sum += Integer.parseInt(line);
            }
            bufferedReader.close();

            System.out.println("Toplam : " + sum);

        } catch(IOException e) {
            throw new RuntimeException(e);
        }












    }
}