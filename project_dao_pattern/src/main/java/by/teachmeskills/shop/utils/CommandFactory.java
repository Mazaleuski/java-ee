package by.teachmeskills.shop.utils;

import by.teachmeskills.shop.commands.AccountPageCommandImpl;
import by.teachmeskills.shop.commands.AddProductToCartCommandImpl;
import by.teachmeskills.shop.commands.BaseCommand;
import by.teachmeskills.shop.commands.CategoryRedirectCommandImpl;
import by.teachmeskills.shop.commands.DeleteProductFromCartCommandImpl;
import by.teachmeskills.shop.commands.HomePageCommandImpl;
import by.teachmeskills.shop.commands.RedirectProductPageCommandImpl;
import by.teachmeskills.shop.commands.RedirectToShoppingCartImpl;
import by.teachmeskills.shop.commands.RegistrationPageCommandImpl;
import by.teachmeskills.shop.commands.SignInCommandImpl;
import by.teachmeskills.shop.enums.CommandsEnum;
import by.teachmeskills.shop.enums.RequestParamsEnum;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, BaseCommand> COMMAND_LIST = new HashMap<>();

    static {
        COMMAND_LIST.put(CommandsEnum.HOME_PAGE_COMMAND.getCommand(), new HomePageCommandImpl());
        COMMAND_LIST.put(CommandsEnum.SIGN_IN_COMMAND.getCommand(), new SignInCommandImpl());
        COMMAND_LIST.put(CommandsEnum.CATEGORY_PAGE_COMMAND.getCommand(), new CategoryRedirectCommandImpl());
        COMMAND_LIST.put(CommandsEnum.ADD_PRODUCT_TO_CART.getCommand(), new AddProductToCartCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_SHOPPING_CART_COMMAND.getCommand(), new RedirectToShoppingCartImpl());
        COMMAND_LIST.put(CommandsEnum.REDIRECT_PRODUCT_COMMAND.getCommand(), new RedirectProductPageCommandImpl());
        COMMAND_LIST.put(CommandsEnum.REGISTRATION_PAGE_COMMAND.getCommand(), new RegistrationPageCommandImpl());
        COMMAND_LIST.put(CommandsEnum.DELETE_PRODUCT_FROM_CART.getCommand(), new DeleteProductFromCartCommandImpl());
        COMMAND_LIST.put(CommandsEnum.ACCOUNT_PAGE_COMMAND.getCommand(), new AccountPageCommandImpl());
    }

    public static BaseCommand defineCommand(HttpServletRequest request) {
        String commandKey = request.getParameter(RequestParamsEnum.COMMAND.getValue());
        if (commandKey == null || commandKey.isEmpty()) {
            commandKey = CommandsEnum.SIGN_IN_COMMAND.getCommand();
        }
        return COMMAND_LIST.get(commandKey);
    }

}

