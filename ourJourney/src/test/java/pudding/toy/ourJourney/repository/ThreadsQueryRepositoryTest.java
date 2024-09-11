package pudding.toy.ourJourney.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pudding.toy.ourJourney.config.ProfileInitializer;
import pudding.toy.ourJourney.entity.Category;
import pudding.toy.ourJourney.entity.Contents;
import pudding.toy.ourJourney.entity.ContentsThread;

@Transactional
@SpringBootTest
class ThreadsQueryRepositoryTest {
    @Autowired
    private ThreadsQueryRepository threadsQueryRepository;
    @Autowired
    private ThreadRepository threadRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ContentRepository contentRepository;
    ProfileInitializer profileInitializer;
    @BeforeEach
    public void setUp(){
        Category category1 = new Category("해외");
        categoryRepository.save(category1);
        Contents contents1 = new Contents("name",category1,null,profileInitializer.dummyProfile);
        contentRepository.save(contents1);
        ContentsThread contentsThread1 = threadRepository.save(new ContentsThread("thread",contents1));
        ContentsThread contentsThread2 = threadRepository.save(new ContentsThread("thread",contents1));
    }
    @Test
    public void getThreadListTest(){

    }
}
