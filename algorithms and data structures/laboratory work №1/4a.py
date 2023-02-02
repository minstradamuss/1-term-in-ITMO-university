stack = []
command = input()
while command != 'exit':
    # push n
    if command.find('push') > -1:
        com, n = command.split(' ')
        stack.append(int(n))
        print('ok')
    # back
    elif command.find('back') > -1:
        if len(stack) > 0:
            print(stack[-1])
    # size
    elif command.find('size') > -1:
        print(len(stack))
    # clear
    elif command.find('clear') > -1:
        stack = []
        print('ok')
    # pop
    elif command.find('pop') > -1:
        if len(stack) > 0:
            print(stack[-1])
            stack.pop()
 
    command = input()
 
print('bye')
