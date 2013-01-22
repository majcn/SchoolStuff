function [] = showresults(result, color)

N = size(result, 1);

for i = 1:N
   plot(result(i, [1, 3, 3, 1, 1]), result(i, [2, 2, 4, 4, 2]), 'color', color);

switch result(i, 5)
case 0
c = 'rdeca';
case 1
c = 'modra';
case 2
c = 'rumena';
case 3
c = 'zelena';
case 4
c = 'siva';
end;

switch result(i, 6)
case 0
s = 'okrogla';
case 1
s = 'kvadratna';
case 2
s = 'pravokotna';
case 3
s = 'ostalo';
end;

text(mean(result(i, [1, 3])), mean(result(i, [2, 4])), [c ' ' s], 'color', color, 'horizontalalignment', 'center');

end;

