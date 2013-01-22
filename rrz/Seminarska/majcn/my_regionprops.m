function props = my_regionprops(im)
    cm00 = central_moments(im, 0, 0);
    up20 = central_moments(im, 2, 0) / cm00;
    up02 = central_moments(im, 0, 2) / cm00;
    up11 = central_moments(im, 1, 1) / cm00;

    covMat = [up20 up11 ; up11 up02];
    [V D] = eig( covMat );
    [D order] = sort(diag(D), 'descend');        %# sort cols high to low
    V = V(:,order);

    props = struct();
    props.MajorAxisLength = 4*sqrt(D(1));
    props.MinorAxisLength = 4*sqrt(D(2));
    props.Eccentricity = sqrt(1 - D(2)/D(1));
    props.Orientation = -atan(2*up11/(up20-up02))/2 * (180/pi);
end

function cmom = central_moments(im,i,j)
    rawm00 = raw_moments(im,0,0);
    centroids = [raw_moments(im,1,0)/rawm00 , raw_moments(im,0,1)/rawm00];
    cmom = sum(sum( (([1:size(im,1)]-centroids(2))'.^j * ...
                     ([1:size(im,2)]-centroids(1)).^i) .* im ));
end

function outmom = raw_moments(im,i,j)
    outmom = sum(sum( ((1:size(im,1))'.^j * (1:size(im,2)).^i) .* im ));
end