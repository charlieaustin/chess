# -*- coding: utf-8 -*-
'''
A module of tools to analyze chess games.
'''


class PgnError(Exception):
    '''Errors in parsing .pgn files and extracting data.'''
    def __init__(self, msg):
        self.msg = msg

    def __str__(self):
        return self.msg


def data_from_Game(game):
    '''
    Get the game data (metadata, moves) from a `chess.pgn.Game` object.

    :param chess.pgn.Game game:
        the game to extract data from

    :returns:
        the metadata of the game (player names, result, etc)
        and the list of moves in UCI notation
    :rtype:
        collections.OrderedDict, list

    '''

    move_list = []
    curr_move = game
    while len(curr_move.variations) > 0:
        if len(curr_move.variations) > 1:
            raise PgnError('Too many variations!')
        curr_move = curr_move.variation(0)
        move_list.append(str(curr_move.move))

    return game.headers, move_list


class Gamestats(object):
    '''
    Tools to import and analyze the moves in a chess game.

    For example, we read in a game from a .pgn file.

    >>> from chess import pgn
    >>> pgn_file = open('data/test_game.pgn')
    >>> first_game = pgn.read_game(pgn_file)

    We make a `Gamestats` object using :func:`data_from_Game`.

    >>> g = Gamestats(*data_from_Game(first_game))
    >>> g.moves[0]
    'e2e4'

    :param dict headers:
        the metadata of the game
    :param list moves:
        the list of moves as strings in UCI notation

    '''

    def __init__(self, headers, moves):
        self.headers = headers
        self.moves = moves
