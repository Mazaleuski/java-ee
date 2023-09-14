package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface BaseCommand {
    String execute(HttpServletRequest request) throws CommandException;
}
