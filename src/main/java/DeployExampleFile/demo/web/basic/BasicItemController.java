package DeployExampleFile.demo.web.basic;



import DeployExampleFile.demo.domain.item.Item;
import DeployExampleFile.demo.domain.item.ItemRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepositroy itemRepositroy;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepositroy.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }



    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepositroy.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }
   // @PostMapping("/add")
    public String save(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model
    ){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepositroy.save(item);
        model.addAttribute("item",item);
        return "basic/item";
    }
    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepositroy.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepositroy.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepositroy.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepositroy.save(new Item("itemA", 10000, 10));
        itemRepositroy.save(new Item("itemB", 20000, 20));
    }
}
