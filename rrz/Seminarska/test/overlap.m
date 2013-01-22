function [D] = overlap(r1, r2)

N = size(r1, 1);
M = size(r2, 1);

D = zeros(N, M);

for i = 1:N
for j = 1:M

if i == j
continue;
end;

D(i, j) = rectangle_overlap(r1(i, :), r2(j, :));

end;
end;

end

function [o] = rectangle_overlap(r1, r2)

    intersection = max(0, min(r1(3), r2(3)) - max(r1(1), r2(1))) ...
        * max(0, min(r1(4), r2(4)) - max(r1(2), r2(2)));

    union = (r1(3) - r1(1)) * (r1(4) - r1(2)) + (r2(3) - r2(1)) * (r2(4) - r2(2));
    
    o = intersection / (union - intersection);

end
