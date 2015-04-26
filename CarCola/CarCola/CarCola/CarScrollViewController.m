//
//  CarScrollViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/13/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#define TAG 99

#import "CarScrollViewController.h"
#import "CardCollectionViewCell.h"

@interface CarScrollViewController ()

@end

@implementation CarScrollViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section
{
    return 1;
}

- (NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView
{
    return  10;
}
-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath
{
     CardCollectionViewCell *cell = (CardCollectionViewCell  *)[collectionView dequeueReusableCellWithReuseIdentifier:@"reuse" forIndexPath:indexPath];
    [self configureCell:cell withIndexPath:indexPath];
    return cell;
}

- (void)configureCell:(CardCollectionViewCell *)cell withIndexPath:(NSIndexPath *)indexPath
{
    UIView  *subview = [cell.contentView viewWithTag:TAG];
    [subview removeFromSuperview];
    
    switch (indexPath.section) {
        case 0:
            cell.imageView.image =  [UIImage imageNamed:@"BMW 4"];
            cell.mainLabel.text = @"BMW 4";
            break;
        case 1:
            cell.imageView.image =  [UIImage imageNamed:@"Cadillac CTS"];
            cell.mainLabel.text = @"Cadillac CTS";
            break;
        case 2:
            cell.imageView.image =  [UIImage imageNamed:@"Ford F-150"];
            cell.mainLabel.text = @"Ford F-150";
            break;
        case 3:
            cell.imageView.image =  [UIImage imageNamed:@"Jaguar XF"];
            cell.mainLabel.text = @"Jaguar XF";
            break;
        case 4:
            cell.imageView.image =  [UIImage imageNamed:@"MB GLK"];
            cell.mainLabel.text = @"MB GLK";
            break;
        case 5:
            cell.imageView.image =  [UIImage imageNamed:@"Mustang GT"];
            cell.mainLabel.text = @"Mustang GT";
            break;
        case 6:
            cell.imageView.image =  [UIImage imageNamed:@"Porsche Panamera"];
            cell.mainLabel.text = @"Porsche Panamera";
            break;
        case 7:
            cell.imageView.image =  [UIImage imageNamed:@"Subaru BRZ"];
            cell.mainLabel.text = @"Subaru BRZ";
            break;
        case 8:
            cell.imageView.image =  [UIImage imageNamed:@"Subaru XV"];
            cell.mainLabel.text = @"Subaru XV";
            break;
        case 9:
            cell.imageView.image =  [UIImage imageNamed:@"Tesla S P85"];
            cell.mainLabel.text = @"Tesla S P85";
            break;

            
        default:
            break;
    }
    
}

- (IBAction)GoBackButton:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];

}

@end
