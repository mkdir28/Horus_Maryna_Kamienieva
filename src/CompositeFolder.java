import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class CompositeFolder implements MultiFolder {
    private String name;
    private String size;
    private List<Folder> folders;

    public CompositeFolder(String name, String size, List<Folder> folders) {
        this.name = name;
        this.size = size;
        this.folders = (folders != null) ? folders : new ArrayList<>();
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getSize() {return size;}

    @Override
    public List<Folder> getFolders() {return folders;}

    public static <T, R> R foldersChecker(List<Folder> folders, T parameter, BiFunction<Folder, T, R> check) {
        R result = resultFolder(check);

        for (Folder folder : folders) {
            R processed = check.apply(folder, parameter);

            if (result instanceof Optional && processed instanceof Optional && ((Optional<?>) processed).isPresent())
                return processed;

            if (result instanceof List && processed instanceof List)
                ((List<Folder>) result).addAll((List<Folder>) processed);

            if (result instanceof Integer && processed instanceof Integer)
                result = (R) Integer.valueOf((Integer) result + (Integer) processed);


            if (folder instanceof MultiFolder) {
                R subResult = foldersChecker(((MultiFolder) folder).getFolders(), parameter, check);

                if (subResult instanceof Optional && ((Optional<?>) subResult).isPresent()) return subResult;

                if (subResult instanceof List) ((List<Folder>) result).addAll((List<Folder>) subResult);

                if (subResult instanceof Integer) result = (R) Integer.valueOf((Integer) result + (Integer) subResult);
            }
        }
        return result;
    }

    private static <T, R> R resultFolder(BiFunction<Folder, T, R> check) {
        if (check.apply(null, null) instanceof Optional) return (R) Optional.empty();
        else if (check.apply(null, null) instanceof List) return (R) new ArrayList<Folder>();
        else return (R) Integer.valueOf(0);
    }

}
