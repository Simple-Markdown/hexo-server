package live.midreamsheep.hexo.server.tool.patch;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;
import com.github.difflib.patch.PatchFailedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FilePatchUtil {
    public static void patch(String filePath, List<String> patch) {
        Patch<String> stringPatch = UnifiedDiffUtils.parseUnifiedDiff(patch);
        List<String> source = null;
        try {
            source = Files.readAllLines(new File(filePath).toPath());
            System.out.println(source.size());
            List<String> patchedText = DiffUtils.patch(source, stringPatch);
            Files.write(new File(filePath).toPath(), patchedText);
        } catch (IOException | PatchFailedException e) {
            throw new RuntimeException(e);
        }
    }
}
