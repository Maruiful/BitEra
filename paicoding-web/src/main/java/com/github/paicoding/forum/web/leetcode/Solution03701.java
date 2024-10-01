package com.github.paicoding.forum.web.leetcode;

public class Solution03701 {
    public void solveSudoku(char[][] board)  {}

    private boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') { // 发现空格
                    for (char c = '1'; c <= '9'; c++) { // 尝试填充数字 '1' 到 '9'
                        if (isValid(board, i, j, c)) { // 检查数字是否符合规则
                            board[i][j] = c; // 填充数字
                            if (solve(board)) { // 递归处理下一个空格
                                return true;
                            } else {
                                board[i][j] = '.'; // 回溯，撤销填充
                            }
                        }
                    }
                    return false; // 如果所有数字都不符合规则，返回 false
                }
            }
        }
        return true; // 所有空格填充完毕，返回 true
    }

    private boolean isValid(char[][] board, int row, int col, char c)  { return false; }

    public static void main(String[] args) {
        Solution03701 solution = new Solution03701();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solution.solveSudoku(board);
        // 输出解答后的数独板
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
                if (j < 8) System.out.print(" ");
            }
            System.out.println();
        }
    }
}
