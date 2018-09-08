package com.application.humming.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.humming.constant.HummingConstants;
import com.application.humming.constant.PageConstants;
import com.application.humming.dto.ItemDto;
import com.application.humming.exception.HummingException;
import com.application.humming.form.SearchForm;
import com.application.humming.service.ItemService;
import com.application.humming.util.PropertiesUtil;

@Controller
@RequestMapping(value = "/")
public class ItemController {

    @Autowired
    HttpSession session;

    @Autowired
    private ItemService itemService;

    @ModelAttribute
    public SearchForm setUpSearchForm() {
        return new SearchForm();
    }

    /**
     * トップ画面を表示する.
     *
     * @return トップ画面
     */
    @RequestMapping(value = "/")
    public String displayTopPage() {
        return PageConstants.TOP_PAGE;
    }

    /**
     * トップ画面に初期表示するアイテム一覧を表示する.<br>
     * OffsetとLimitに値を入れることで、表示数を変更できる.
     *
     * @return アイテム一覧
     * @throws HummingException
     */
    @RequestMapping(value = "/displayInitialItemList")
    @ResponseBody
    public List<ItemDto> displayInitialItemList() throws HummingException {
        return itemService.getInitialItemList(HummingConstants.OFFSET, HummingConstants.LIMIT);
    }

    /**
     * アイテム詳細を表示する.
     *
     * @return アイテム詳細画面
     */
    @RequestMapping(value = "/detail")
    public String displsyDetailPage(@RequestParam(name = "itemId") Integer id, Model model) {
        final ItemDto itemDto = itemService.getItemDetail(id);
        model.addAttribute("item", itemDto);
        return PageConstants.DETAIL_PAGE;
    }

    /**
     * アイテムを検索する.
     *
     * @return アイテム検索画面
     */
    @RequestMapping(value = "/search")
    public String displaySearchPage(SearchForm searchForm, Model model) {
        final String singerOrSong = searchForm.getSingerOrSong();
        // 検索キーワード無し → ユーザーメッセージを表示する
        if (StringUtils.isEmpty(singerOrSong)) {
            model.addAttribute("searchResultMessage", PropertiesUtil.getProperties("search.empty.message"));
            return PageConstants.SEARCH_PAGE;
        }
        final List<ItemDto> itemDtoList = itemService.getItemWithSingerOrSong(singerOrSong);
        // 検索一致アイテム無し → ユーザーメッセージを表示する
        if (CollectionUtils.isEmpty(itemDtoList)) {
            model.addAttribute("searchResultMessage", PropertiesUtil.getProperties("search.not.found.message"));
            return PageConstants.SEARCH_PAGE;
        }
        // 検索一致アイテム有り → 検索アイテムを表示する
        model.addAttribute("searchResultMessage", HummingConstants.SEARCH_RESULT_PREFIX + singerOrSong + HummingConstants.SEARCH_RESULT_SUFFIX);
        model.addAttribute("itemList", itemDtoList);

        return PageConstants.SEARCH_PAGE;
    }
}