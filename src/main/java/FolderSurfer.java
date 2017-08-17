import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FolderSurfer {

//    public static int f(int x) {
//        return 2 * x + 1;
//    }
//
//    public static Function<Integer, Integer> ff = (x) -> 2 * x + 1;

    public static void main(String[] args){

//        assert f(2) == 5;
//        assert ff.apply(2) == 5;

        String Current_path = System.getProperty("user.dir");
        List<String> parameters = Arrays.asList(args);

        File folder = new File(Current_path);
        List<File> folderItems = Arrays.asList(folder.listFiles());

        final List<File> filesList = folderItems.stream().filter(File::isFile).collect(Collectors.toList());
        final List<File> foldersList = folderItems.stream().filter(File::isDirectory).collect(Collectors.toList());
        File output_file = new File("file_list.txt");

        try (
             BufferedWriter bw_out = new BufferedWriter(new FileWriter(output_file));
             OutputStreamWriter output_stream = new FileWriter(output_file);) {


            String complete_path = "";

            if (parameters.contains("FullPath") || parameters.contains("/full")) {
                complete_path = Current_path;
            }
            if (parameters.contains("Files") || parameters.contains("/F")) {
                // collect file names
                foldersList.forEach(file -> output_stream.write("\n" + "(F)" + complete_path + file.getName()));
            }
            if (parameters.contains("Dirs") || parameters.contains("/D")) {
                // collect dir names
                foldersList.forEach(file -> output_stream.write("\n" + "(D)" + complete_path + file.getName()));
            }
            System.out.println("\n Output File Generated : file_list.txt");
        } catch (Exception e){
            System.out.print(e.getMessage());
        }

    }
}
