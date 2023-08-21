package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.utils.DataUtil.getCategoriesFromDB;

public class HomePageCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("categories", getCategoriesFromDB());
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}