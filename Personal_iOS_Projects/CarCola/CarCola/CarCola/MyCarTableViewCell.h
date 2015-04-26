//
//  MyCarTableViewCell.h
//  CarCola
//
//  Created by Hao Zhou on 3/5/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "GalleryTableViewController.h"


@interface MyCarTableViewCell : UITableViewCell
- (IBAction)rateButton:(id)sender;
- (IBAction)goToGallery:(id)sender;

@property (weak, nonatomic) IBOutlet UILabel *carMakeInCell;
@property (weak, nonatomic) IBOutlet UILabel *carModelInCell;
@property (weak, nonatomic) IBOutlet UILabel *carTrimInCell;
@property (weak, nonatomic) IBOutlet UILabel *carYearInCell;
@property (weak, nonatomic) IBOutlet UILabel *carColorInCell;
@property (weak, nonatomic) IBOutlet UILabel *carNotesInCell;
//@property (weak, nonatomic) IBOutlet UIImageView *carImage;

@property (weak, nonatomic) IBOutlet UIImageView *rateStar;

@property NSMutableArray *sentData;

-(NSInteger)getFromDefault;
-(void)saveToDefault:(NSInteger)rate;


@end
