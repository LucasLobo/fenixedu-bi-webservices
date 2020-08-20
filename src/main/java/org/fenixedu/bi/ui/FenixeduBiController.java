package org.fenixedu.bi.ui;

import org.fenixedu.bennu.spring.portal.SpringApplication;
import org.fenixedu.bennu.spring.portal.SpringFunctionality;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/fenixedu-bi")
@SpringApplication(group = "logged", path = "fenixedu-bi", title = "title.FenixeduBi")
@SpringFunctionality(app = FenixeduBiController.class, title = "title.FenixeduBi")
public class FenixeduBiController {

    @RequestMapping
    public String home(Model model) {
        model.addAttribute("world", "World");
        return "fenixedu-bi/home";
    }

}
