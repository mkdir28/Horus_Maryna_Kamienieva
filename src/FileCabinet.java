import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet{
    private List<Folder> folders;

    public FileCabinet(){folders = new ArrayList<Folder>();}

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return findFolderNaming(folders, name);
    }

    private Optional<Folder> findFolderNaming(List<Folder> folders, String name){
        for (Folder folder : folders) {
            if (folder.getName().equals(name)) {
                return Optional.of(folder);
            }
            if (folder instanceof MultiFolder) {
                Optional<Folder> found = findFolderNaming(((MultiFolder) folder).getFolders(), name);
                if (found.isPresent()) {
                    return found;
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return findSizeFolders(folders, size);
    }

    private List<Folder> findSizeFolders(List<Folder> folders, String size){
        List<Folder> showResult = new ArrayList<>();
        for (Folder folder : folders) {
            if (folder.getSize().equals(size)) {
                showResult.add(folder);
            }
            if (folder instanceof MultiFolder) {
                showResult.addAll(findSizeFolders(((MultiFolder) folder).getFolders(), size));
            }
        }
        return showResult;
    }

    @Override
    public int count() {
        return countFolders(folders);
    }

    private int countFolders(List<Folder> folders){
        int countFolders = folders.size();
        try{
            for(Folder folder : folders){
                if(folder instanceof MultiFolder){
                    countFolders += countFolders(((MultiFolder)folder).getFolders());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return countFolders;
    }
}
