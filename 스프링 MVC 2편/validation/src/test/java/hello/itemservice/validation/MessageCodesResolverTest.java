package hello.itemservice.validation;


import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject(){
        // 객체 오류
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
//        for (String messageCode : messageCodes) {
//            System.out.println("messageCode = " + messageCode);
//        }
        assertThat(messageCodes).containsExactly("required.item", "required");
//        new ObjectError("item", new String[]{"required.item", "required"});
    }

    @Test
    void messageCodesResolverField(){
        // 필드 오류
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
//        BindingResult.rejectValue("itemName","required");
//        new FieldError("item","itemName",null,false,messageCodes,null,null);
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }
}
