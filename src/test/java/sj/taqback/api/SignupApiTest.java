package sj.taqback.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import sj.taqback.controller.SignupForm;
import sj.taqback.domain.User;
import sj.taqback.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Transactional
public class SignupApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateUser() throws Exception {
        SignupForm form = createForm("id", "pw", "nick");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(form);

        mockMvc.perform(post("/users/new")
                        .contentType("application/json")
                        .content(jsonRequest));

        User user = userRepository.findByAccountId("id").get();
        String nickname = user.getNickname();

        Assertions.assertThat(nickname).isEqualTo("nick");
    }

    SignupForm createForm(String accountId, String password, String nickname) {
        SignupForm form = new SignupForm();
        form.setAccountId(accountId);
        form.setPassword(password);
        form.setNickname(nickname);

        return form;
    }
}


