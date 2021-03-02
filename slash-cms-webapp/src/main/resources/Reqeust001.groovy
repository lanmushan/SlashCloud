import org.springframework.stereotype.Service
import org.springframework.ui.ModelMap

import javax.servlet.http.HttpServletRequest

@Service("groovy001")
class Groovy001 {
    def String doHandler(Map<String, Object> requestBody, ModelMap model, Map<String, Object> data, HttpServletRequest request) {
        return null;
    }
}
