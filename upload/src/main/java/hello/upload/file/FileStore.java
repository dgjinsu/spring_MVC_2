package hello.upload.file;

import hello.upload.domain.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    /**
     * 개별 파일 저장
     */
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFilename, storeFileName);
    }

    /**
     * 여러 파일 저장
     */
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()) {
                UploadFile uploadFile = storeFile(multipartFile);
                storeFileResult.add(uploadFile);
            }
        }
        return storeFileResult;
    }

    /**
     * ex) C:/Users/(storeFileName).jpg 반환
     */
    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    /**
     * 이름 중복을 피하기 위해 UUID.(파일타입) 로 storeFileName 생성
     */
    private String createStoreFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + extractExt(originalFilename); //qweqwewqe.png
        return storeFileName;
    }

    /**
     * 파일 타입 추출 메서드
     */

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1);
        return ext;
    }

}
