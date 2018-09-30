package com.application.humming.controller;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private ItemService itemService;

    @ModelAttribute
    public SearchForm setUpSearchForm() {
        return new SearchForm();
    }

    /** アイテムリストモデル. */
    private static final String ITEM_LIST_MODEL = "itemList";
    /** アクティブページモデル. */
    private static final String ACTIVE_PAGE_MODEL = "activePage";
    /** ページング数モデル. */
    private static final String PAGING_COUNT_MODEL = "pagingCount";
    /** 検索結果メッセージモデル. */
    private static final String SEARCH_RESULT_MESSAGE_MODEL = "searchResultMessage";

    /**
     * トップ画面を表示する.
     *
     * @return トップ画面
     * @throws HummingException
     */
    @RequestMapping(value = "/")
    public String displayTopPage(Model model) throws HummingException {
        model.addAttribute(ITEM_LIST_MODEL, itemService.getInitialItemList(HummingConstants.OFFSET, HummingConstants.LIMIT));
        model.addAttribute(ACTIVE_PAGE_MODEL, NumberUtils.INTEGER_ONE);
        model.addAttribute(PAGING_COUNT_MODEL, itemService.calculatePagingCount());
        return PageConstants.TOP_PAGE;
    }

    /**
     * 選択されたページ番号に応じたトップ画面を表示する.
     *
     * @return トップ画面
     * @throws HummingException
     */
    @RequestMapping(value = "/page")
    public String displayTopPaging(@RequestParam(name = "number") Integer number, Model model) throws HummingException {
        final int offset = HummingConstants.LIMIT * (number - NumberUtils.INTEGER_ONE);
        model.addAttribute(ITEM_LIST_MODEL, itemService.getInitialItemList(offset, HummingConstants.LIMIT));
        model.addAttribute(ACTIVE_PAGE_MODEL, number);
        model.addAttribute(PAGING_COUNT_MODEL, itemService.calculatePagingCount());
        return PageConstants.TOP_PAGE;
    }

    /**
     * アイテム詳細を表示する.
     *
     * @return アイテム詳細画面
     */
    @RequestMapping(value = "/detail")
    public String displsyDetailPage(@RequestParam(name = "itemId") Integer itemId, Model model) {
        model.addAttribute("item", itemService.getItemDetail(itemId));
        return PageConstants.ITEM_DETAIL_PAGE;
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
            model.addAttribute(SEARCH_RESULT_MESSAGE_MODEL, PropertiesUtil.getProperties("search.empty.message"));
            return PageConstants.ITEM_SEARCH_PAGE;
        }
        final List<ItemDto> itemDtoList = itemService.getItemWithSingerOrSong(singerOrSong);
        // 検索一致アイテム無し → ユーザーメッセージを表示する
        if (CollectionUtils.isEmpty(itemDtoList)) {
            model.addAttribute(SEARCH_RESULT_MESSAGE_MODEL, PropertiesUtil.getProperties("search.not.found.message"));
            return PageConstants.ITEM_SEARCH_PAGE;
        }
        // 検索一致アイテム有り → 検索アイテム名を表示する
        model.addAttribute(SEARCH_RESULT_MESSAGE_MODEL, HummingConstants.SEARCH_RESULT_PREFIX + singerOrSong + HummingConstants.SEARCH_RESULT_SUFFIX);
        model.addAttribute(ITEM_LIST_MODEL, itemDtoList);

        return PageConstants.ITEM_SEARCH_PAGE;
    }
}