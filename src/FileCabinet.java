import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet{
    private List<Folder> folders;

    public FileCabinet(){folders = new ArrayList<Folder>();}

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return CompositeFolder.foldersChecker(folders, name, (folder, checkName) ->
                folder.getName().equals(checkName) ? Optional.of(folder) : Optional.empty()
        );
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return CompositeFolder.foldersChecker(folders, size, (folder, checkSize) ->
                folder.getSize().equals(checkSize) ? List.of(folder) : List.of()
        );
    }

    @Override
    public int count() {
        return CompositeFolder.foldersChecker(folders, null, (folder, unused) -> 1);
    }
}
