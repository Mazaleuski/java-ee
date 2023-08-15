package by.teachmeskills.commands;

import by.teachmeskills.exceptions.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface BaseCommand {
    String execute(HttpServletRequest request) throws CommandException;
}
