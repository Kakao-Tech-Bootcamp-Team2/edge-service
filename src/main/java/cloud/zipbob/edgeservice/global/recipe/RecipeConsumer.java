package cloud.zipbob.edgeservice.global.recipe;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeConsumer {

    private final BlockingQueue<String> recipeQueue = new LinkedBlockingQueue<>();

    //TODO 큐 이름 수정
    @RabbitListener(queues = "recipe.queue")
    public void receiveRecipe(String recipe) {
        log.info("레시피를 받았습니다.");
        try {
            recipeQueue.put(recipe);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted while adding recipe to the queue");
        }
    }

    public String getRecipe() throws InterruptedException {
        return recipeQueue.take();
    }
}